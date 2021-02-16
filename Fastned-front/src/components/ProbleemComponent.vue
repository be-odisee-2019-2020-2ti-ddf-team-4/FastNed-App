<template>
  <div id="probleem">
   
   <div>
   <h1>probleem aanmeldingen</h1>
   <div class="container"> 
     
     <div class="containerObject"  >
      <label for="searchOption">Zoeken op :</label> <br>

      <select id="searchOption" @change="onchange()"  v-model="key">
        <!-- <option value="id">id</option> -->
        <option value="status">status</option>
        
      </select>
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
    <div v-if=" probleems == '' ">
        Jammer genoeg, werd er geen problemen gevonden.<br/><br/>
    </div>

    
    <div v-else  >
       
         
      <table>

        <tr>
          <th>beschrijving</th>
          <th>status</th>
          <th>oplossing</th>
        </tr>  

        <!-- for each methode in vue.js stijl -->
        <tr v-for="probleem in probleems">      
          <td>{{ probleem.beschrijving}}</td>
          <td>{{ probleem.status}}</td>
          <td>{{ probleem.oplossing}}</td>
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
        name: "probleem",
        data () {
            return {
                toLookUp: '',
                theUrl: '',
                key: '',
                urlPart: '',
                
                info: null,
                status: null,
                error: null,
                probleems: [             
                ]
            }
        },
        mounted () {
            this.toLookUp = ''
            this.getToLookUp()
        },
        methods: {
          
            onchange: function() {
                console.log(this.key)
                switch (this.key) {

                  case "id":
                      this.urlPart = "";
                      console.log(this.urlPart)
                      break;

                  case "status":
                      this.urlPart = "search/findByStatusIsLike?status=";
                      console.log(this.urlPart)
                      break;  

                }
            },
            getToLookUp() {
                   this.theUrl = 'http://localhost:8089/probleems/' + this.urlPart + this.toLookUp
                   const headers = {
                    withCredentials: true
                }

                axios
                    .get(this.theUrl, headers)
                    .then(response => (this.info = response.data,
                                        this.status = response.status,
                                        this.probleems = response.data._embedded.probleems,
                                        console.log(this.info)) )
                   
                    .catch(error => (this.info = "error",
                                        this.status = error.response.status,
                                        this.error = error.response.data.message ) )
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

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

    margin: 1em auto;
    width: 100%;
    text-align: center;
}

select#searchOption {
  
    width: 12em;

}

</style>
