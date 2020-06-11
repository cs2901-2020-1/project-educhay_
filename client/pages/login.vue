<template>
  <v-container class="fill-height my-style">
    <v-row align="center" justify="center">
      <v-col class="text-center">
        <h1 class="m-5">Iniciar sesion</h1>
        <v-form>
          <v-text-field
            v-model="form.email"
            type="email"
            label="Correo electronico"
            outlined
          />
          <v-text-field
            v-model="form.password"
            type="password"
            label="Contraseña"
            outlined
          />
          <v-btn @click="onSubmit">
            submit
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      form: {
        email: '',
        password: ''
      }
    }
  },
  methods: {
    async onSubmit() {
      const url = ''
      await this.$axios
        .$post(url)
        .then((res) => {})
        .catch((e) => {})
    }
  }
}
</script>

<style>
.my-style {
  max-width: 420px;
}
</style>
