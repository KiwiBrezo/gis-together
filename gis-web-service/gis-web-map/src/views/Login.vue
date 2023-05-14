<template>
  <div class="grid grid-cols-1 grid-rows-3 gap-2">
    <input type="text" v-model="this.username" placeholder="Username" class="text-black">
    <input type="password" v-model="this.password" placeholder="Password" class="text-black">
    <button @click="this.loginUser">Prijavi se!</button>
  </div>
</template>

<script>
import axios from "axios";
import {UserStore} from "@/stores/user";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: ""
    }
  },
  methods: {
    async loginUser() {
      const user = {
        username: this.username,
        password: this.password
      }

      try {
        const res = await axios.post("http://localhost:3000/api/v1/account/login", user);

        UserStore().setToken(res.data)

        if (UserStore().isLogedIn()) {
          this.$router.push("/map");
        }
      } catch (e) {
        console.log(e);
      }
    }
  }
}
</script>

<style scoped>

</style>