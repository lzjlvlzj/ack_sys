<template>
  <div class="footer" :style="{backgroundImage: 'url(' + footerImgUrl + ')' }">
    <el-row>
      <el-col :span="16" :offset="4" class="link">
        <span class="l">友情链接：</span>
        <div class="a">
          <a href="http://www.nhc.gov.cn/" target="_blank">中华人民共和国国家卫生健康委员会</a>
          <a href="http://wjw.beijing.gov.cn/" target="_blank">北京市卫生健康委员会</a>
          <a href="http://www.cams.ac.cn/" target="_blank">中国医学科学院</a>
          <a href="http://www.pumf.org/" target="_blank">北京协和医学基金会</a>
          <a href="http://www.xiehekjkf.com/" target="_blank">协和科技开发有限责任公司</a>
          <a class="last" href="http://www.xiehejx.com/" target="_blank">协和化妆品</a>
        </div>
      </el-col>
    </el-row>
    <el-row class="copy">
      <el-col :span="16" :offset="4" class="link">
          <div class="left">
            <div class="siteMap">
              <a href="/sitemap.html">网站地图</a>
              <a href="/research/gudie.html">关于我们</a>
            </div>
            <p class="cf address">
              <span>地　　址：</span>
              <span class="div">
                <b>（东单院区）北京市东城区帅府园一号，100730；</b>
                <b>（西单院区）北京市西城区大木仓胡同41号，100032</b>
              </span>
            </p>
            <p>服务热线： 010-69156114</p>
          </div>
          <div class="right">
            <p>Copyright 北京协和医院 All Rights Reserved. 本站内容仅供参考，不作为诊断及医疗依据 <br> 京ICP 备 05067313号-1 文保网安备案号：1101010023
              京卫网审字【2014】第39号</p>
          </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        footerImgUrl: "../static/img/foot.jpg",
        footerSlicerImgUrl: "../static/img/foot_1.jpg",
      }
    }
  }
</script>
<style>
  .footer {
    width: 100%;
    background-size: cover;
    font-size: 12px;
  }
  .footer .link {
    padding: 26px 0;
    border-bottom: 1px solid #585858;
    color: #b2b2b2;
    line-height: 20px;
  }
  .footer .link .a {
    overflow: hidden;
  }
  .footer .copy {
    padding: 34px 0 30px;
  }
  .footer .copy .siteMap a {
    color: #b2b2b2;
    float: left;
  }
  .footer .copy .left {
    float: left;
    width: 50%;
    color: #b2b2b2;
    line-height: 20px;
  }

  .footer .copy .right {
    float: right;
    text-align: right;
    color: #b2b2b2;
    line-height: 20px;
    width: 45%;
  }
  .footer .link .a a {
    background: url('~@/../static/img/foot_1.png') no-repeat right center;
    padding-right: 21px;
    margin-right: 21px;
  }

  .footer .link .a a {
    color: #b2b2b2;
    float: left;
  }

  .footer .link .a a.last {
    margin: 0;
    padding: 0;
    background: none;
  }

  .footer .copy .siteMap a + a {
    padding-left: 42px;
    background: url('~@/../static/img/foot_1.png') no-repeat 21px center;
  }
</style>
