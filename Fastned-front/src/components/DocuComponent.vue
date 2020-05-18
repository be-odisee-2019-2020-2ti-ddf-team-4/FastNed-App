<template>
  <div id="app">
   
   <div>
   <h1>Documentatie laadpaal</h1>
   <div class="container"> 
     
     <div class="containerObject"  > 
      <label for="searchOption">Zoeken op Laadpaal naam</label> <br>

    </div>
    <div class="containerObject" >
      <!-- event handler in vue.js stijl -->
      <form @submit.prevent="getToLookUp">
        <input type="text" placeholder="" v-model="toLookUp" name="toLookUp"><br>        
      </form>
    </div>
</div>
    </div>

      <br/>

    <!-- if else methode in vue.js stijl -->
    <div v-if=" documentaties == '' ">
        Jammer genoeg, werd er geen documentatie gevonden.<br/><br/>
    </div>

    
    <div v-else  >
      <div v-for="documentatie in documentaties">
        <h2 @click.prevent="getToLookUp" name="toLookUp">{{documentatie.laadpaalType}} </h2>
        <div v-show="show" >{{documentatie.documentatieBeschr}}</div>
      </div>
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
        name: "Documentatie",
        data () {
          
            return {
              show: true,
                toLookUp: '',
                theUrl: '',
                key: '',
                urlPart: '',
                
                info: null,
                status: null,
                error: null,
                documentaties: [             
                ]
            }
        },
        mounted () {
            this.toLookUp = ''
            this.getToLookUp()
        },
        methods: {
          
            onchange: function() {               
            },
            
            getToLookUp() {
                  this.urlPart = "search/findByLaadpaalTypeStartingWith?laadpaalType=";
                  console.log(this.urlPart)
                   this.theUrl = 'http://localhost:8082/documentaties/' + this.urlPart + this.toLookUp
                   const headers = {
                    withCredentials: true
                }

                axios
                    .get(this.theUrl, headers)
                    .then(response => (this.info = response.data,
                                        this.show = !this.show,
                                        this.status = response.status,
                                        this.documentaties = response.data._embedded.documentaties,
                                        console.log(this.info)) )
                   
                    .catch(error => (this.info = "error",
                                        this.status = error.response.status,
                                        this.error = error.response.data.message ) )
            },
            
        
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 0 10px;
}
a {
  color: #42b983;
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  
  margin-left:auto;
  margin-right:auto;
  margin-bottom: 1em;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.container {
    margin: 1em auto;
    
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
