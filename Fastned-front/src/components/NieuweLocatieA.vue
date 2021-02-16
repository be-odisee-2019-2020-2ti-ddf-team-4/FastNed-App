<template>
    <div id="nieuweLocatieA">
        <input type="hidden" id="id" name="id" value="0">

        
        <div class="form-group "> 
            <label>Eigenaar voornaam:</label> <br>
            <input type="text" id="ownerVoornaam" name="ownerVoornaam" v-model="entryData.ownerVoornaam"  >  <br>

            <label>Eigenaar naam:</label> <br>
            <input type="text" id="ownerNaam" name="ownerNaam"   v-model="entryData.ownerNaam" >  <br>

            <label>Straatnaam:</label> <br>
            <input type="text" id="straatnaam" name="straatnaam" v-model="entryData.straatnaam" >  <br> 

            <label>Huisnummer:</label> <br>
            <input  type="text" id="huisnummer" name="huisnummer" v-model="entryData.huisnummer" >  <br> 

            <label>Gemeente:</label> <br>
            <input type="text" id="gemeente" name="gemeente" v-model="entryData.gemeente" >  <br>

            <label>Postcode:</label> <br>
            <input type="text" id="postcode" name="postcode" v-model="entryData.postcode" >  <br>

        </div>
       
        <div class="form-group"  >
            <div >
               
                <button type="submit" class="btnPost"
                        v-on:click="test"  name="submit" style=" margin-right: 1em">Meld locatie</button>
                <div style="clear: both"></div>
            </div>
            <div style="clear: both"></div>
        </div>
        <div class="well" >
            <span id="output" v-text="message"></span>
        </div>
    </div>
</template>

<script>
    
    import axios from "axios";
    

    export default {
        name: "nieuweLocatieA",
        
        data: function() {
        return {
                    "entryData": {
                        
                        "ownerVoornaam": "",
                        "ownerNaam": "",
                        "straatnaam": "",
                        "huisnummer": "",
                        "gemeente": "",
                        "postcode": "",
                        
                        
                        
                    },
                    "message": "Vul uw locatie in, a.u.b."
                };
        },
        methods: { 
            
            check: function() {

                var l = document.getElementById('ownerVoornaam')
            },
            test: function() {
            
                var voornaam = document.getElementById('ownerVoornaam');
                var naam = document.getElementById('ownerNaam');
                var straatnaam = document.getElementById('straatnaam');
                var huisnummer = document.getElementById('huisnummer');
                var gemeente = document.getElementById('gemeente');
                var postcode = document.getElementById('postcode');               

                if( 
                voornaam.value == "" || 
                naam.value == "" || 
                straatnaam.value == "" || 
                huisnummer.value == "" || 
                gemeente.value == "" || 
                postcode.value == ""  
                )
                {
                    let output = document.getElementById('output')
                    output.style.color = "red";
                    output.style.fontWeight = "bolder";

                    this.message = "Please fill in all fields"
                    setTimeout(() => {
                        this.message = "Vul uw locatie in, a.u.b."
                    }, 3000);
                }
                
                
            },
            
            
                      
            submitForm: function () {
                const url = 'http://localhost:8081/locatieAanmeldings';
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
        mounted() {
this.check()
        }
    }
</script>

