export function findDepartments(params){
  let res = {}
  res.url = "/dept"
  res.method = "get"
  let rt = {
    "code":"200",
    "msg":"success",
    "data":[
      {
        "id":"1",
        "color":"#67C23A",
        "name": "内科",
        "intro": "内科学是临床医学的一个专科，几乎是所有其他临床医学的基础，亦有医学之母之称。",
        "imgUrl": "../../static/img/ks-1.jpg",
        "url":"/dept/1",
        "children":[
          {
            "id":1,
            "name":"内外科",
            "url":""
          },
          {
            "id":2,
            "name":"内分泌科",
            "url":""
          },
          {
            "id":3,
            "name":"神经内科",
            "url":""
          },
          {
            "id":4,
            "name":"中风内科",
            "url":""
          },
          {
            "id":5,
            "name":"肛肠内科",
            "url":""
          },
          {
            "id":6,
            "name":"消化内科",
            "url":""
          },
          {
            "id":7,
            "name":"肾内科",
            "url":""
          },
        ]
      },
      {
        "id":"2",
        "color":"#E6A23C",
        "name": "外科",
        "intro": "外科学是临床医学的一个专科，几乎是所有其他临床医学的基础，亦有医学之母之称。",
        "imgUrl": "../../static/img/wk-1.jpg",
        "url":"/dept/2",
        "children":[
          {
            "id":1,
            "name":"整形外科",
            "url":""
          },
          {
            "id":2,
            "name":"普通泌科",
            "url":""
          },
          {
            "id":3,
            "name":"五官外科",
            "url":""
          },
          {
            "id":4,
            "name":"器官移植",
            "url":""
          },
          {
            "id":5,
            "name":"肛肠外科",
            "url":""
          },
          {
            "id":6,
            "name":"肝胆外科",
            "url":""
          },
          {
            "id":7,
            "name":"骨科",
            "url":""
          },
        ]
      },
      {
        "id":"3",
        "color":"#409EFF",
        "name": "医技",
        "intro": "医技学是临床医学的一个专科，几乎是所有其他临床医学的基础，亦有医学之母之称。",
        "imgUrl": "../../static/img/yj-2.jpg",
        "url":"/dept/3",
        "children":[
          {
            "id":1,
            "name":"放射科",
            "url":""
          },
          {
            "id":2,
            "name":"病理科",
            "url":""
          },
          {
            "id":3,
            "name":"检验科",
            "url":""
          },
          {
            "id":4,
            "name":"化验科",
            "url":""
          },
          {
            "id":5,
            "name":"护理部",
            "url":""
          },
          {
            "id":6,
            "name":"功能科",
            "url":""
          },
          {
            "id":7,
            "name":"核医学科",
            "url":""
          },
        ]
      }
    ]}
  res.data = rt
  return res
}
