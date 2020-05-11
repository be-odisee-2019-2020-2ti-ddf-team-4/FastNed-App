<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">

    <Afspraken/>
  </div>
</template>

<script>
import Afspraken from '@/components/Afspraken.vue'

export default {
  name: 'Home',
  components: {
    Afspraken
  }
}
</script>
