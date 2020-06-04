var tool = {}
tool.clone = function (obj) {
  var buf;
  if (obj instanceof Array) {
    buf = [];
    var i = obj.length;
    while (i--) {
      buf[i] = clone(obj[i]);
    }
    return buf;
  } else if (obj instanceof Object) {
    buf = {};
    for (var k in obj) {
      buf[k] = clone(obj[k]);
    }
    return buf;
  } else {
    return obj;
  }

}

export default tool
