<template>
  <v-container class="fill-height my-style">
    <v-row align="center" justify="center">
      <v-col class="text-center">
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
    onSubmit() {
      alert(JSON.stringify(this.form))
    }
  }
}
</script>

<style>
.my-style {
  max-width: 420px;
}
</style>
