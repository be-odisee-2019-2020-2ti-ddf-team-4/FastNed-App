<template>
  <div id="app">
    <div id="nav">
      <router-link to="/home">Home</router-link> |
      <router-link to="/">Locatie aanmelding</router-link> |
      <router-link to="/about">About</router-link>
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
</style>
