<template>
  <div class="hello">
    <h1>{{ msg }}</h1><br>
    
    
<p >Bent u eigenaar en wilt u een van onze snel laadpalen op uw locatie(s)?</p>
<p><strong> Dat kan hier!</strong></p>
    <button><router-link to="/NieuweLocatieAanmelding">Locatie aanmelden</router-link></button>
    
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: rgb(251, 212, 50);
}



</style>
