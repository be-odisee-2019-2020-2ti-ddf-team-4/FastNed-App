<template>
    <div id="divAfspraken">
        <div class="well">
            <!-- OVHD, NETW, PROSP, FULF, REND, TRAVEL -->

            <span>
                <label >Afspraak</label><br>
                <select name="afspraken" v-model="selectedAfspraakId"
                        @change="onChangeSelectedAfspraak(selectedAfspraakId,$event)" >
                    <option v-for="afspraak in this.afspraken"
                            v-bind:value="afspraak.id" >{{ afspraak.id }}</option>
                </select>&nbsp;
            </span>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "afspraken-form",
        data() {
            return {
                "afspraken": {},
                "contracten": new Array(),
                "installateurs": new Array(),
                "laadpalen": new Array(),
                "bezoeken": new Array(),
                "selectedAfspraakId": 0
            }
        },
        methods: {
            onChangeSelectedAfspraak:function(afspraakIndex, event){
                for (var afspraakId in this.projectSelectedInCategory) {
                    this.projectSelectedInCategory[categoryId] = +0;
                }
                const aProjectId = +event.target.value;
                this.projectSelectedInCategory[categoryIndex] = aProjectId;
                this.$emit('prj-selected', this.projectSelectedInCategory);
            },
        },
        created: function() {
            // this will only work when a served from a webserver
            url = 'http://localhost:8080/fastnedrest/afspraken'

            axios.get(url, { withCredentials: true })
                .then( (response) => {
                    console.log("afspraken")
                    console.log(response.status)
                    console.log(response.data);
                    this.afspraken = response.data;
                });
        }
    }
</script>

<style scoped>
    select, label {
        margin-left: 0.5em;
    }
</style>