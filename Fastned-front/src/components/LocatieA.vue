<template>
  <div id="locatieAanmelding" class="container"  >
    
   <div>
   <h1>Locatie aanmeldingen</h1>
   <div class="container"> 
     
     <div class="containerObject"  >
      <label for="searchOption">Zoeken op :</label> <br>

      <select id="searchOption" @change="onchange()"  v-model="key">
        <!-- <option value="id">id</option> -->
        <option value="eigenaarVoornaam">eigenaar Voornaam</option>
        <option value="eigenaarNaam">eigenaar naam</option>
        <option value="straatnaam">straatnaam</option>
        <option value="gemeente">gemeente</option>
        <option value="postcode">postcode</option>
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
    <div v-if=" locaties == '' ">
        Jammer genoeg, werd er geen locatie aanmelding gevonden.<br/><br/>
    </div>

    
    <div v-else  >
       
         
      <table>

        <tr>
          <th>Eigenaar voornaam</th>
          <th>Eigenaar naam</th>
          <th>Straatnaam</th>
          <th>Huisnummer</th>
          <th>Gemeente</th>
          <th>Postcode</th>
        </tr>  

        <!-- for each methode in vue.js stijl -->
        <tr v-for="locatie in locaties">      
          <td>{{ locatie.ownerVoornaam}}</td>
          <td>{{ locatie.ownerNaam}}</td>
          <td>{{ locatie.straatnaam}}</td>
          <td>{{ locatie.huisnummer }}</td>
          <td>{{ locatie.gemeente }}</td>
          <td>{{ locatie.postcode }}</td>
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
        name: "LocatieAanmelding",
        
        data () {
            return {
                toLookUp: '',
                theUrl: '',
                key: '',
                urlPart: '',
                
                info: null,
                status: null,
                error: null,
                locaties: [             
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

                  case "eigenaarVoornaam":
                      this.urlPart = "search/findByOwnerVoornaamStartingWith?voornaam=";
                      console.log(this.urlPart)
                      break;  

                  case "eigenaarNaam":
                      this.urlPart = "search/findByOwnerNaamStartingWith?naam=";
                      console.log(this.urlPart)
                      break;

                  case "straatnaam":
                      this.urlPart = "search/findByStraatnaamStartingWith?straatnaam=";
                      console.log(this.urlPart)
                      break;  

                  case "gemeente":
                      this.urlPart = "search/findByGemeenteStartingWith?gemeente=";
                      console.log(this.urlPart)
                      break;

                  case "postcode":
                      this.urlPart = "search/findByPostcodeStartingWith?postcode=";
                      console.log(this.urlPart)
                      break;

                  default:
                      console.log("You didn't enter a valid number")
                }
            },
            getToLookUp() {
                   this.theUrl = 'http://localhost:8081/locatieAanmeldings/' + this.urlPart + this.toLookUp
                   const headers = {
                    withCredentials: true
                }

                axios
                    .get(this.theUrl, headers)
                    .then(response => (this.info = response.data,
                                        this.status = response.status,
                                        this.locaties = response.data._embedded.locatieAanmeldings,
                                        console.log(this.info)) )
                   
                    .catch(error => (this.info = "error",
                                        this.status = error.response.status,
                                        this.error = error.response.data.message ) )
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >


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
