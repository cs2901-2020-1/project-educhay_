<template>
  <v-container>
    <v-row align="start" justify="center" class="mt-5">
      <v-col class="text-center" md="3">
        <h1 class="m-5">Iniciar sesión</h1>
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
          <v-btn color="primary lighten-2" class="mt-3" @click="onSubmit"
            >submit</v-btn
          >
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
