<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <BlogList/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import BlogList from '@/views/blog/BlogList.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'HomeView',
  components: {
    BlogList
  },
});
</script>
