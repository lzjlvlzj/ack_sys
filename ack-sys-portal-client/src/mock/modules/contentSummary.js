export function findContentSummary(data){
  let res = {}
  res.url = "/content/summary"
  res.method = "post"
  let rt  = {
    "code":"200",
    "msg":"success",
    "data": [
      {
        "id":0,
        "sourceType":0,
        "sourceName":"医院公告",
        "sourceImgUrl":"../../static/img/cs-1.jpg",
        "titles":[
          {
            "id":0,
            "title" : "北京协和医院2020年五一门诊工作安排",
            "url":"/article/0/0" /*/article/文章类别/文章id*/
          },
          {
            "id":1,
            "title" : "北京协和医院试剂采购公告（第35期）",
            "url":"/article/0/1"
          },
          {
            "id":2,
            "title" : "北京协和医院试剂采购公告（第34期）",
            "url":"/article/0/2"
          },
        ]

      },
      {
        "id":1,
        "sourceType":1,
        "sourceName":"医院新闻",
        "sourceImgUrl":"../../static/img/cs-2.jpg",
        "titles":[
          {
            "id":3,
            "title" : "北京协和医院2020年五一门诊工作安排",
            "url":"/article/1/3" /*/article/文章类别/文章id*/
          },
          {
            "id":4,
            "title" : "北京协和医院试剂采购公告（第35期）",
            "url":"/article/1/4"
          },
          {
            "id":6,
            "title" : "北京协和医院试剂采购公告（第34期）",
            "url":"/article/1/5"
          },
        ]

      },
      {
        "id":2,
        "sourceType":2,
        "sourceName":"健康科普",
        "sourceImgUrl":"../../static/img/cs-3.png",
        "titles":[
          {
            "id":6,
            "title" : "北京协和医院2020年五一门诊工作安排",
            "url":"/article/2/6" /*/article/文章类别/文章id*/
          },
          {
            "id":7,
            "title" : "北京协和医院试剂采购公告（第35期）",
            "url":"/article/2/7"
          },
          {
            "id":8,
            "title" : "北京协和医院试剂采购公告（第34期）",
            "url":"/article/2/8"
          },
        ]

      },
    ]
  }
  res.data = rt
  return res
}
