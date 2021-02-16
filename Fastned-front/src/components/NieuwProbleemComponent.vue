<template>
    <div id="NieuwProbleem">
        <input type="hidden" id="id" name="id" value="0">

        
        <div class="form-group "> 
            <label>Beschrijving:</label> <br>
            <input type="text" id="beschrijving" name="beschrijving" v-model="entryData.beschrijving"  >  <br>

            <label>Status:</label> <br>
            <input type="text" id="status" name="status"   v-model="entryData.status" >  <br>

        </div>
       
        <div class="form-group"  >
            <div >
               
                <button type="submit" class="btnPost"
                        v-on:click="submitForm"  name="submit" style=" margin-right: 1em">Meld Probleem</button>
                <div style="clear: both"></div>
            </div>
            <div style="clear: both"></div>
        </div>
        <div class="well" >
            <span v-text="message"></span>
        </div>
    </div>
</template>

<script>
    
    import axios from "axios";
    

    export default {
        name: "nieuwProbleem",
        
        data: function() {
        return {
                    "entryData": {
                        
                        "beschrijving": "",
                        "status": "",
                        
                        
                        
                    },
                    "message": "Vul uw probleem in, a.u.b."
                };
        },
        methods: {         
                      
            submitForm: function () {
                const url = 'http://localhost:8089/probleems';
                const headers = {
                    withCredentials: true
                }

                axios.post(url, this.entryData, headers)
                    .then( (response) => {
                        console.log(response.status)
                        console.log(response.data);
                    })
                    .catch(function (error) {
                        console.log(error)
                    });
                    

                this.message = "EntryData submitted: " + JSON.stringify(this.entryData);

                
            }
        },
    }
</script>

<style scoped>
    input {
       
        margin: 1em;
        background-color: rgba(255,255,255,0.9);

        width: 90%;

          height: 3em;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: 3px solid #ccc;
  -webkit-transition: 0.5s;
  transition: 0.5s;
  outline: none;
}

input[type=text]:focus {
  border: 3px solid #555;
}
        
    

    .form-group{
        text-align: center;
    }
     .btnPost{
         background-color: #313236;
    border: none;
    color: yellow;
    padding: 1em;
    text-align: center;
    font-weight: bold;
    margin: 0em;
    cursor: pointer;
    border-radius: 0.75em;
     }

.well{
    margin-bottom: 0em;
}
   
</style>