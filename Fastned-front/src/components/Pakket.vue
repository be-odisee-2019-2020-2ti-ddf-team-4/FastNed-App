<template>
  <div id="app">


    <h2>Proficiaat u hebt een reis van travelbase gewonnen!</h2>
     <h3>Jouw keuzes bestaat uit het volgende:</h3>


   <!-- if else methode in vue.js stijl -->
    <div v-if=" pakkets == '' ">
        Jammer genoeg, werd er geen pakket gevonden.<br/><br/>
    </div>


     <div v-else>
    <table>
     
        <tr>
          <th>pakket naam </th>
          <th>beschrijving </th>
        </tr>
      <tr v-for="pakket in pakkets">
        <td>{{ pakket.naam}}</td>
        <td>{{ pakket.description}}</td>
      </tr>

    </table>

    </div>

    <div>
      <strong>Status: </strong> {{ status }}
    </div>
    <div v-if= " info == 'error' " >
      <strong>Error: </strong> {{ error }}
    </div>
  </div>

</template>

<script>

  import * as axios from "axios";

export default {
  name: 'Pakketen',
  data () {
    return {
      toLookUp: '',
      theUrl: '',
       key: '',
      urlPart: '',


      info: null,
      status: null,
      error: null,
      pakkets: [
      ]
    }
  },
  mounted () {
    this.toLookUp = ''
    this.getToLookUp()
  },
  methods:{
    onchange: function() {               
            },
    getToLookUp() {
      this.theUrl = 'http://localhost:8084/pakkets/'
      const headers = {
        withCredentials: true
      }

      axios
              .get(this.theUrl, headers)
              .then(response => (this.info = response.data,
                      this.show = !this.show,
                      this.status = response.status,
                      this.pakkets = response.data._embedded.pakkets,
                      console.log(this.info)) )

              
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 80%;
  margin-left:auto;
  margin-right:auto;
  margin-bottom: 1em;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.container {
  margin: 1em auto;
  width: 30%;
  padding: 1em;
  background-color: rgba(211,211,211,0.7);
  border-radius: 1em;
}

.containerObject{

  margin: 1em;
  width: 100%;
  text-align: center;
}

select#searchOption {

  width: 12em;

}
</style>