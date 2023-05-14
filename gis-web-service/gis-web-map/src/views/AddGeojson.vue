<template>
  <iframe src="http://localhost:3006/" frameborder="0" class="geo-import-iframe" id="geo-import-iframe"></iframe>
</template>

<script>
import {UserStore} from "@/stores/user";

export default {
  name: "AddGeojson",
  mounted() {
    let frame = document.querySelector("#geo-import-iframe");
    frame.contentWindow.postMessage({call:'sendValue', value: UserStore().getToken()});
  }
}
</script>

<style scoped>
.geo-import-iframe {
  width: 500px;
  height: 800px;
}
</style>