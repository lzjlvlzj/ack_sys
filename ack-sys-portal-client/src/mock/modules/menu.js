/**
 * 菜单
 * */
export function findMenu() {
  let res = {}
  res.url = "/menu/nav"
  res.method = "get"
  res.data = {
    "code": 200,
    "msg": "success",
    "data": [{
      "id":1,
      "name": "首页",
      "url": "/",
      "icon": "",
      "index":"1",
      "children":[]
    }, {
      "id":2,
      "name": "医院简介",
      "url": "/",
      "icon": "",
      "index":"2",
      "children":[]
    }, {
      "id":3,
      "name": "医生介绍",
      "url": "/",
      "icon": "",
      "index":"3",
      "children":[
        {
          "id":4,
          "name": "孙思邈",
          "url": "/",
          "icon": "",
          "index":"3-1",
          "children": []
        },
        {
          "id":5,
          "name": "张仲景",
          "url": "/",
          "icon": "",
          "index":"3-2",
          "children": []
        },
        {
          "id":6,
          "name": "华佗",
          "url": "/",
          "icon": "",
          "index":"3-3",
          "children": []
        },
      ]

    }
      , {
        "id":7,
        "name": "就医指南",
        "url": "/",
        "icon": "",
        "index":"4",
        "children":[]
      }
      , {
        "id":8,
        "name": "医院公告",
        "url": "/",
        "icon": "",
        "index":"5",
        "children":[]
      },
      {
        "id":9,
        "name": "新闻中心",
        "url": "/",
        "icon": "",
        "index":"6",
        "children":[]
      }
    ]
  }
  return res
}
