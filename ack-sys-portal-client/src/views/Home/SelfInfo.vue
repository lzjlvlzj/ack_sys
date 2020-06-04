<template>
  <div class="box box-border-none" id="self-info">
    <div class="box-header box-header-content">我们的成长</div>
    <div class="box-body self-content">
      <ul>
        <li class="">
          <a>
            <img :src="djImgUrl"/>
            <div class="tit">
              <h3>党建工作</h3>
              <i></i>
              <span>查看更多</span>
            </div>
          </a>
        </li>
        <li class="">
          <a>
            <img :src="yjImgUrl"/>
            <div class="tit">
              <h3>学术研究</h3>
              <i></i>
              <span>查看更多</span>
            </div>
          </a>
        </li>
        <li class="">
          <a>
            <img :src="ygImgUrl"/>
            <div class="tit">
              <h3>员工风采</h3>
              <i></i>
              <span>查看更多</span>
            </div>
          </a>
        </li>
        <li class="">
          <a>
            <img :src="zpImgUrl"/>
            <div class="tit">
              <h3>人才招聘</h3>
              <i></i>
              <span>查看更多</span>
            </div>
          </a>
        </li>

      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: "SelfInfo",
    data() {
      return {
        djImgUrl:"../../../static/img/dj-2.jpg",
        yjImgUrl:"../../../static/img/yj-1.jpg",
        zpImgUrl:"../../../static/img/zp-1.png",
        ygImgUrl:"../../../static/img/yg-1.jpg"
      }
    }
  }
</script>

<style scoped>
  #self-info {
    min-height: 320px;
    width: 100%;
    position: inherit;
    margin-top: 15px;
  }

  .self-content {
    width: 100%;
    padding: 0;
  }
  #self-info li {
    float: left;
    width: 25%;
    position: relative;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  }

  #self-info li a {
    display: block;
    padding-bottom: 88.3%;
  }


  @media only screen and (max-width: 768px){
    #self-info li {
      width: 50%;
      height:auto;
    }
  }

  #self-info li>a img {
    position: absolute;
    display: block;
    width: 100%;
    top: 0;
    left: 0;
  }

  @media only screen and (max-width: 768px){
    .self-info li .tit {
      height: 1.8rem;
      margin-top: -0.8rem;
    }
  }
  @media only screen and (min-width: 1023px){
    #self-info li .tit {
      height: 90px;
      margin-top: -45px;
    }

  }
  #self-info li .tit {
    position: absolute;
    width: 90%;
    left: 5%;
    text-align: center;
    top: 50%;
    height: 120px;
    margin-top: -60px;
    color: #fff;
    z-index: 20;
  }

  #self-info .tit h3 {
    font-size: 18px;
    line-height: 1.5em;
    height: 3em;
  }

  #self-info .tit i {
    display: block;
    width: 38px;
    margin: 25px auto;
    height: 1px;
    background: #fff;
    transition: all 0.3s;
    -webkit-transition: all 0.3s;
  }



  #self-info li .tit span {
    font-size: 12px;
    line-height: 1;
    display: block;
  }
</style>
