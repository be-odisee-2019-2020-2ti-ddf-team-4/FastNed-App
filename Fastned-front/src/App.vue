<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/NieuweLocatieAanmelding">Locatie aanmelden</router-link> |
      <router-link to="/LocatieAanmelding">Aanmelding bekijken</router-link> |      
      <router-link to="/Documentatie">Documentatie</router-link> |
      <router-link to="/Probleem">Problemen bekijken</router-link> |
      <router-link to="/NieuwProbleem">Probleem aanmelden</router-link> |
      <router-link to="/TravelbasePakket">Reispakket</router-link> |
      <router-link to="/about">About</router-link> |
      
    </div>
    <div >
    <router-view/>
    </div>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
}
 

 body{
  background-color: white;
  background-image: url("./assets/highwayWallpaper3.jpg");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
  margin: 0em;
}

html{
  height: 100%;
}

.container{
    margin: 1em auto;
    width: fit-content;
    padding: 1em;
    background-color: rgba(211,211,211,0.7);
    border-radius: 1em;
}

.about.container{
    margin: 1em auto;
    width: 50%;
    padding: 1em;
    background-color: rgba(211,211,211,0.9);
    border-radius: 1em;
}

#nav {
  padding: 30px;
  background-color: #313236;
  
    text-align: center;
}

#nav a {
  font-weight: bold;
  color: grey;
}

#nav a.router-link-exact-active {
  color: rgb(251, 212, 50);
}

button, .btn{
  background-color: #313236;
    border: none;
    color: yellow;
    padding: 1em;
    text-align: center;
    font-weight: bold;
    margin: 1em 0.5em;
    cursor: pointer;
    border-radius: 0.75em;
}

button:hover {
  background-color:#3d3e43;
}

h1, h2 {
  
  font-weight: bold;
}

h1{
  margin: 0em;
}

h2 {
  margin: 40px 0 0;
}

p{
  color: black;
  font-size: 1.5em;
}

#mainform, div#locatieAanmelding {
  text-align: center;
}

input  {
       
        margin: 1em;
        background-color: rgba(255,255,255,0.9);

        width: 90%;

          height: 3em;
  padding: 0px 20px;
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
