export function findContentSummary(data){
  let res = {}
  res.url = "/content/summary"
  res.method = "post"
  let rt  = {
    "code":"200",
    "msg":"success",
    "data": {
      "title":"一位年轻女孩在协和的“心”生之路",
      "auth" : "医科圣手",
      "source":"原创",
      "":""
    }
  }
  res.data = rt
  return res
}
