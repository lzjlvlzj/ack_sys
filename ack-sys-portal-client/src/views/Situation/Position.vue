<template>
  <baidu-map :center="center" :zoom="zoom" @ready="handler" style="height:600px" @click="getClickInfo" :scroll-wheel-zoom='true'>
  </baidu-map>
</template>

<script>
  export default {
    name: "Position",
    data() {
      return {
        center: {lng: 114.382461, lat: 38.320817},
        zoom: 18
      }
    },
    methods: {
      handler ({BMap, map}) {
        var point = new BMap.Point(114.382461,38.320817)
        map.centerAndZoom(point, 18)
        var marker = new BMap.Marker(point) // 创建标注
        map.addOverlay(marker) // 将标注添加到地图中
        var circle = new BMap.Circle(point, 6, { strokeColor: 'Red', strokeWeight: 6, strokeOpacity: 1, Color: 'Red', fillColor: '#f03' })
        map.addOverlay(circle)
      },
      getClickInfo (e) {
        console.log(e.point.lng)
        console.log(e.point.lat)
        this.center.lng = e.point.lng
        this.center.lat = e.point.lat
      }
    }
  }
</script>

<style scoped>

</style>
