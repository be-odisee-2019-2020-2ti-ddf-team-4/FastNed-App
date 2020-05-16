<template>
  <div id="locatieAanmelding" class="container">
    <h1>Vul hier uw locatie in </h1>

    

    <MainForm />

  </div>
</template>

<script>
// @ is an alias to /src
import MainForm from '@/components/MainForm.vue'

export default {
  name: 'LocatieAanmelding',
  components: {
    MainForm
  }
}
</script>
