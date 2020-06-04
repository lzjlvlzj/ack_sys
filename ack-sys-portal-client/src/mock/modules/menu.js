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
      "parentId": 0,
      "children":[]
    }, {
      "id":2,
      "name": "医院概况",
      "url": "/situation",
      "icon": "",
      "index":"2",
      "parentId": 0,
      "children":[
        {
          "id":21,
          "name": "医院简介",
          "url": "/situation/intro",
          "icon": "",
          "index":"",
          "parentId": 2,
          "children": []
        },
        {
          "id":22,
          "name": "地理位置",
          "url": "/situation/position",
          "icon": "",
          "index":"",
          "parentId": 2,
          "children": []
        }
      ]
    }, {
      "id":3,
      "name": "医生介绍",
      "url": "/staff",
      "icon": "",
      "index":"3",
      "children":[
        {
          "id":4,
          "name": "孙思邈",
          "url": "",
          "icon": "",
          "index":"3-1",
          "parentId": 2,
          "children": []
        },
        {
          "id":5,
          "name": "张仲景",
          "url": null,
          "icon": "",
          "index":"3-2",
          "parentId": 3,
          "children": []
        },
        {
          "id":6,
          "name": "华佗",
          "url": null,
          "icon": "",
          "index":"3-3",
          "parentId": 3,
          "children": []
        },
      ]

    }
      , {
        "id":7,
        "name": "就医指南",
        "url": "/medicalGuide",
        "icon": "",
        "index":"4",
        "parentId": 0,
        "children":[]
      }
      , {
        "id":8,
        "name": "医院公告",
        "url": "/notice",
        "icon": "",
        "index":"5",
        "parentId": 0,
        "children":[]
      },
      {
        "id":9,
        "name": "新闻中心",
        "url": "/news",
        "icon": "",
        "index":"6",
        "parentId": 0,
        "children":[]
      }
    ]
  }
  return res
}
