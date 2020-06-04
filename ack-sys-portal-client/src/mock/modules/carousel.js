export function findCarouselImages(params){
  let res = {}
  res.url = "/carousel/images"
  res.method = "get"
  let rt = {
    "code":"200",
    "msg":"success",
    "data":[
    {
      id:"1",
      name: "11",
      label: "11",
      imgUrl: "../../static/img/c-1.jpg",
      hrefUrl: ""
    },
    {
      id:"2",
      name: "11",
      label: "11",
      imgUrl: "../../static/img/c-2.jpg",
      hrefUrl: ""
    },
    {
      id:"3",
      name: "11",
      label: "11",
      imgUrl: "../../static/img/c-3.jpg",
      hrefUrl: ""
    },
    {
      id:"4",
      name: "11",
      label: "11",
      imgUrl: "../../static/img/c-4.jpg",
      hrefUrl: ""
    }
  ]}
  res.data = rt
  return res
}
