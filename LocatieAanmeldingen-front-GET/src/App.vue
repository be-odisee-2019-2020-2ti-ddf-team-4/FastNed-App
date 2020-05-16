<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <LocatieAanmelding msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import LocatieAanmelding from './components/LocatieAanmelding.vue'

export default {
  name: 'app',
  components: {
    LocatieAanmelding
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
  
}


</style>
