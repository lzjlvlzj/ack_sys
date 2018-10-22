package org.ack.admin.web.view;


import org.ack.pojo.*;
import org.ack.util.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 2003
 * excel导出视图查询的实体类
 *
 * @author ack
 */
public class ExcelView extends AbstractXlsView {
    private static Logger logger = LoggerFactory.getLogger(ExcelView.class);

    String[] columns = { "品牌", "产品名称", "编码", "数量", "单位" , "规格", "单价", "金额", "是否是赠品", "备注"};
    String companyName = "河北百娇化妆品销售有限公司";
    String fileName = "";//文件名称
    String[] clientInfo = {"客户名称", "客户地址", "客户电话"};
    String storeName = "品牌名称";
    String[] logisticsInfo = {"托运物流","物流电话"};
    String totalPriceInfo = "价格总计";
    String balanceInfo = "账户余额";
    String[] tableInfo = {"制表人","制表时间"};


    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Trade trade = (Trade)map.get("trade");
        fileName = getFileName(trade);
        User user = (User)map.get("user");
        String userName = user.getSurname() + user.getName();

        HSSFWorkbook wb = (HSSFWorkbook)workbook;
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("销售单");
        //sheet.setDefaultColumnWidth((short) 24);
        // 设置标题
        setTital(wb, sheet, trade);
        // 设置客户名称
        setClientName(wb, sheet, trade);
        // 设置客户地址和电话
        setClientContact(wb, sheet, trade);
        // 物流信息
        setLogistics(wb, sheet, trade);
        // 设置产品明细
        setTradeDetail(wb, sheet, trade);
        // 设置总金额
        //setTotalPrice(wb, sheet, trade);
        // 设置余额
        setBalance(wb, sheet, trade);
        // 设置制表人信息
        setTableInfo(wb, sheet, trade, userName);


        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-disposition", "attachment;filename="
                + "aaaa.xls");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();

    }

    /**
     * 制表人
     * @param wb
     * @param sheet
     * @param trade
     * @param userName
     */
    private void setTableInfo(HSSFWorkbook wb, HSSFSheet sheet, Trade trade, String userName) {
        // 计算行数
        int size = trade.getTradeItems().size();
        //制表人
        int rowNum = 5 + size + 1;
        HSSFRow makerRow = sheet.createRow(rowNum);
        HSSFCell makerKeyCell = makerRow.createCell(0);
        makerKeyCell.setCellValue(tableInfo[0]);
        HSSFCell makerValueCell = makerRow.createCell(1);
        makerValueCell.setCellValue(userName);

        CellRangeAddress makerRegion = new CellRangeAddress (rowNum,  rowNum, 1, 2);
        sheet.addMergedRegion(makerRegion);

        //制表时间
        HSSFCell makerTimeKeyCell = makerRow.createCell(3);
        makerTimeKeyCell.setCellValue(tableInfo[1]);
        HSSFCell makerTimeValueCell = makerRow.createCell(4);
        Date time = trade.getUpdateTime();
        if(null == time){
            logger.info("销售单修改时间为空,生成excel默认当前时间");
            time = new Date();
        }
        String timeStr = StringUtils.date2String(time);
        makerTimeValueCell.setCellValue(timeStr);

        CellRangeAddress makerTimeRegion = new CellRangeAddress (rowNum,  rowNum, 4, 5);
        sheet.addMergedRegion(makerTimeRegion);
    }

    /**
     * 销售单产品细节
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setTradeDetail(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        List<TradeItem> tradeItemList = trade.getTradeItems();
        //产品细节头信息
        HSSFRow titalRow = sheet.createRow(4);
        CellStyle borderStype = this.getDetailStyle(wb);
        CellStyle titalStyle = this.getDeTailTitalStyle(wb);
        int len = columns.length;
        for(int i = 0; i < len; i++){
            HSSFCell cell = titalRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(titalStyle);
        }
        List<TradeItem> list = trade.getTradeItems();
        int size = list.size();
        for(int i = 0; i <size; i++ ){
            TradeItem item = list.get(i);
            //创建一行
            HSSFRow row = sheet.createRow(4 + i + 1);
            //设置具体数据
            //品牌
            HSSFCell brandCell = row.createCell(0);
            brandCell.setCellValue(item.getBrand().getName());
            brandCell.setCellStyle(borderStype);
            //产品名称
            HSSFCell productNameCell = row.createCell(1);
            productNameCell.setCellValue(item.getProduct().getName());
            productNameCell.setCellStyle(borderStype);
            //产品编号
            HSSFCell productCodeCell = row.createCell(2);
            productCodeCell.setCellValue(item.getProduct().getCode());
            productCodeCell.setCellStyle(borderStype);
            //数量
            HSSFCell productAmountCell = row.createCell(3);
            productAmountCell.setCellValue(item.getAmount());
            productAmountCell.setCellStyle(borderStype);
            //单位
            HSSFCell productUnitCell = row.createCell(4);
            productUnitCell.setCellValue(item.getProduct().getUnit());
            productUnitCell.setCellStyle(borderStype);
            //规格
            HSSFCell productSepcCell = row.createCell(5);
            productSepcCell.setCellValue(item.getProduct().getSpec());
            productSepcCell.setCellStyle(borderStype);
            //单价
            HSSFCell productUnitPriceCell = row.createCell(6);
            productUnitPriceCell.setCellValue(item.getUnitPrice().doubleValue());
            productUnitPriceCell.setCellStyle(borderStype);
            //总价
            HSSFCell productTotalPriceCell = row.createCell(7);
            productTotalPriceCell.setCellValue(item.getTotalPrice().doubleValue());
            productTotalPriceCell.setCellStyle(borderStype);
            //是否是赠品
            HSSFCell productTypeCell = row.createCell(8);
            int type = item.getType();
            String typeStr = (type==0 ? "" :"赠品");
            productTypeCell.setCellValue(typeStr);
            productTypeCell.setCellStyle(borderStype);
            //备注
            HSSFCell remarkCell = row.createCell(9);
            remarkCell.setCellValue(item.getRemark());
            remarkCell.setCellStyle(borderStype);

        }
    }


    /**
     * 余额
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setBalance(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        // 计算行数
        int size = trade.getTradeItems().size();
        CellStyle cellStyle = this.getLeftStyle(wb);
        //
        int rowNum = 5 + size;
        HSSFRow row = sheet.createRow(rowNum);
        HSSFCell coinKeyCell = row.createCell(0);
        coinKeyCell.setCellValue(balanceInfo);
        HSSFCell coinValueCell = row.createCell(1);
        coinValueCell.setCellStyle(cellStyle);
        BigDecimal coin = trade.getAccount().getCoin();
        coin = coin.setScale(2, BigDecimal.ROUND_HALF_UP);
        coinValueCell.setCellValue(coin.doubleValue());
        CellRangeAddress coinRegion = new CellRangeAddress (rowNum,  rowNum, 1, 5);
        sheet.addMergedRegion(coinRegion);

        // 总价:
        HSSFCell priceKeyCell = row.createCell(6);
        priceKeyCell.setCellValue(totalPriceInfo);
        HSSFCell priceValueCell = row.createCell(7);
        priceValueCell.setCellStyle(cellStyle);
        Double price = 0.0;

        List<TradeItem> list = trade.getTradeItems();
        for(TradeItem item : list){
            price = price +  item.getTotalPrice().doubleValue();
        }
        BigDecimal decimal = new BigDecimal(price);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        priceValueCell.setCellValue(decimal.doubleValue());

        CellRangeAddress totalPriceRegion = new CellRangeAddress (rowNum,  rowNum, 7, 9);
        sheet.addMergedRegion(totalPriceRegion);
    }

    /**
     * 物流信息
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setLogistics(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        CellStyle valueStyle = this.getTitalStyle(wb);
        HSSFRow row = sheet.createRow(3);
        HSSFCell vehicleKeyCell = row.createCell(0);
        HSSFCell vehicleValueCell = row.createCell(1);
        Logistics logistics = trade.getLogistics();
        vehicleKeyCell.setCellValue(logisticsInfo[0]);
        vehicleValueCell.setCellValue(logistics.getVehicle());
        vehicleValueCell.setCellStyle(valueStyle);

        CellRangeAddress addressRegion = new CellRangeAddress (3,  3, 1,  3);
        sheet.addMergedRegion(addressRegion);

        HSSFCell phoneKeyCell = row.createCell(4);
        phoneKeyCell.setCellValue(logisticsInfo[1]);
        HSSFCell phoneValueCell = row.createCell(5);
        phoneValueCell.setCellValue(logistics.getPhone());
        phoneValueCell.setCellStyle(valueStyle);

        CellRangeAddress phoneRegion = new CellRangeAddress (3,  3, 5, 9);
        sheet.addMergedRegion(phoneRegion);
    }

    /**
     * 联系方式
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setClientContact(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        CellStyle valueStyle = this.getTitalStyle(wb);
        HSSFRow row = sheet.createRow(2);
        HSSFCell addressKeyCell = row.createCell(0);
        HSSFCell addressValueCell = row.createCell(1);
        Client client = trade.getClient();
        addressKeyCell.setCellValue(clientInfo[1]);
        addressValueCell.setCellValue(client.getAddress());
        addressValueCell.setCellStyle(valueStyle);

        CellRangeAddress addressRegion = new CellRangeAddress (2, 2, 1,  3);
        sheet.addMergedRegion(addressRegion);

        HSSFCell phoneKeyCell = row.createCell(4);
        phoneKeyCell.setCellValue(clientInfo[2]);
        HSSFCell phoneValueCell = row.createCell(5);
        phoneValueCell.setCellValue(client.getPhone());
        phoneValueCell.setCellStyle(valueStyle);

        CellRangeAddress phoneRegion = new CellRangeAddress (2, 2, 5,  9);
        sheet.addMergedRegion(phoneRegion);

    }

    /**
     * 客户名称信息
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setClientName(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        //客户名称
        HSSFRow row = sheet.createRow(1);
        HSSFCell namekeyCell = row.createCell(0);
        HSSFCell nameValueCell = row.createCell(1);
        Client client = trade.getClient();
        namekeyCell.setCellValue(clientInfo[0]);
        nameValueCell.setCellValue(client.getName());
        CellStyle valueStyle = this.getTitalStyle(wb);
        nameValueCell.setCellStyle(valueStyle);

        CellRangeAddress titalRegion = new CellRangeAddress (1, 1, 1,  3);
        sheet.addMergedRegion(titalRegion);

        HSSFCell poskeyCell = row.createCell(4);
        poskeyCell.setCellValue("仓库地址");
        HSSFCell posValueCell = row.createCell(5);
        posValueCell.setCellValue("蕙漫香");
        posValueCell.setCellStyle(valueStyle);

        CellRangeAddress posRegion = new CellRangeAddress (1,  1, 5, 9);
        sheet.addMergedRegion(posRegion);


    }

    /**
     * 报表头
     * @param wb
     * @param sheet
     * @param trade
     */
    private void setTital(HSSFWorkbook wb, HSSFSheet sheet, Trade trade) {
        HSSFRow titalRow = sheet.createRow(0);
        HSSFCell titalCell = titalRow.createCell(0);
        titalCell.setCellValue(companyName);

        CellStyle celltype = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 22);        //字体
        font.setBold(true);                            //加粗
        celltype.setFont(font);
        titalCell.setCellStyle(celltype);
        CellRangeAddress titalRegion = new CellRangeAddress (0,  0, 0,  9);
        sheet.addMergedRegion(titalRegion);
    }

    /**
     * 获得文件名称
     * @param trade
     * @return
     */
    private String getFileName(Trade trade) throws UnsupportedEncodingException {
        //客户名称_手机号_时间
        Client client = trade.getClient();
        String name = client.getName();
        String phone = client.getPhone();
        String fomat = "yyyy-MM-dd-HH:mm:ss";
        String date = StringUtils.date2String(trade.getUpdateTime(), fomat);
        String fileName = name + "_" + phone + "_" + date + ".xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        return fileName;
    }

    private CellStyle  getTitalStyle(HSSFWorkbook wb){
        CellStyle celltype = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBold(true);                            //加粗
        celltype.setFont(font);
        return  celltype;
    }

    private CellStyle  getDetailStyle(HSSFWorkbook wb){
        CellStyle cellType = getLeftStyle(wb);
        cellType.setBorderBottom(BorderStyle.THIN);
        cellType.setBorderLeft(BorderStyle.THIN);
        cellType.setBorderRight(BorderStyle.THIN);
        cellType.setBorderTop(BorderStyle.THIN);
        return  cellType;
    }

    private CellStyle  getLeftStyle(HSSFWorkbook wb){
        CellStyle cellType = wb.createCellStyle();
        cellType.setAlignment(HorizontalAlignment.LEFT);
        return  cellType;
    }

    private  CellStyle getDeTailTitalStyle(HSSFWorkbook wb){
        CellStyle cellType = wb.createCellStyle();
        cellType.setAlignment(HorizontalAlignment.CENTER);
        cellType.setBorderBottom(BorderStyle.THIN);
        cellType.setBorderLeft(BorderStyle.THIN);
        cellType.setBorderRight(BorderStyle.THIN);
        cellType.setBorderTop(BorderStyle.THIN);
        HSSFFont font = wb.createFont();
        font.setBold(true);                            //加粗
        cellType.setFont(font);
        return  cellType;
    }

}
