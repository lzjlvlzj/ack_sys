export function fileUtil(file, config) {
  this.file = file;
  this.config = config;
};
/**
 * 获得后缀
 * @param fn
 * @returns {string}
 */
fileUtil.prototype.getSuffix = function (fn) {
  let fileName = fn || this.file.name;
  fileName = fileName.toLowerCase();
  let p = fileName.lastIndexOf(".");
  let suffix = fileName.substring(p);
  return suffix;
};
/**
 * 校验文件后缀
 */
fileUtil.prototype.checkSuffix = function () {
  let fileSuffix = this.getSuffix();
  let configSuffixes = this.config.suffixes;
  let b = false;
  let rt = {};
  for (let i in configSuffixes) {
    let suffix = configSuffixes[i].trim();
    if (fileSuffix === suffix) {
      b = true;
      break;
    }
  }
  if (!b) {
    rt.code = 400;
    rt.msg = "文件格式不正确，仅支持:" + configSuffixes.join(",");
  } else {
    rt.code = 200;
    rt.msg = "success";
  }
  return rt;
};
/**
 * 校验文件大小
 */
fileUtil.prototype.checkSize = function () {
  let fileSize = this.file.size;
  let configSize = this.config.size;/*这里单位是Mb*/
  let mbSize = fileSize / 1024 / 1024;
  let rt = {};
  if (mbSize > configSize) {
    rt.code = 400;
    rt.msg = "文件大小超过限制('" + configSize * 1024 + "kb')";
  } else {
    rt.code = 200;
    rt.msg = "success";
  }
  return rt;
};

export default fileUtil;
