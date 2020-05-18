<template>
  <div id="nieuweLocatieAanmelding" class="container">
    <h1>Vul hier uw locatie in </h1>

    

    <NieuweLocatieA />

  </div>
</template>

<script>
// @ is an alias to /src
import NieuweLocatieA from '@/components/NieuweLocatieA.vue'

export default {
  name: 'LocatieAanmelding',
  components: {
    NieuweLocatieA
  }
}
</script>
