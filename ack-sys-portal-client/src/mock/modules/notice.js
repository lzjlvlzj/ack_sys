/**
 * 菜单
 * */
export function findMenu() {
  let res = {}
  res.url = "/notices"
  res.method = "get"
  res.data = {
    "code": 200,
    "msg": "success",
    "data": {
      "title":"医院公告",
      "totalRecord":35,
      "pageSize":10,
      "list":
        [{
          "id": 1,
          "title": "去氧胆酸注射液注射治疗颏下脂肪堆积患者招募",
          "type":"公告",
          "url": "/detail/11111",
          "icon": "",
          "time": "2020-05-08",
        },
          {
            "id": 2,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail/11111",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 3,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail/11111",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 2,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail/11111",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 3,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail/11111",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 2,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail/11111",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 3,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 2,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 3,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 2,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail",
            "icon": "",
            "time": "2020-05-08",
          },
          {
            "id": 3,
            "title": "北京协和医院2020年端午节门诊安排",
            "type":"公告",
            "url": "/detail",
            "icon": "",
            "time": "2020-05-08",
          },

        ]
    }
  }
  return res
}
