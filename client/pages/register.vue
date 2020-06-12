<template>
  <v-container>
    <v-row align="start" justify="center" class="mt-5">
      <v-col class="text-center" md="3">
        <h1 class="m-5">Registro</h1>
        <v-form>
          <v-text-field
            v-model="form.name"
            type="text"
            label="Nombres"
            outlined
          />
          <v-text-field
            v-model="form.lastName"
            type="text"
            label="Apellidos"
            outlined
          />
          <v-text-field
            v-model="form.email"
            type="email"
            label="Correo electronico"
            outlined
          />
          <v-text-field
            v-model="form.schoolName"
            type="text"
            label="Colegio"
            outlined
          />
          <v-text-field
            v-model="form.password"
            type="password"
            label="Contraseña"
            outlined
          />
          <v-btn color="primary" class="mt-3" @click="onSubmit">submit</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { validationMixin } from 'vuelidate'
import { required, maxLength, email } from 'vuelidate/lib/validators'
export default {
  mixins: [validationMixin],
  validations: {
    form: {
      email: { required, email, maxLength: maxLength(10) },
      password: { required, maxLength: maxLength(8) }
    }
  },
  data() {
    return {
      form: {
        name: '',
        lastName: '',
        schoolName: '',
        email: '',
        password: ''
      }
    }
  },
  computed: {
    emailErrors() {
      const errors = []
      if (!this.$v.form.email.$dirty) return errors
      !this.$v.form.email.email && errors.push('Must be valid e-mail')
      !this.$v.form.email.required && errors.push('E-mail is required')
      return errors
    },
    passwordErrors() {
      const errors = []
      if (!this.$v.form.password.$dirty) return errors
      !this.$v.form.password.maxLength &&
        errors.push('Password must be at least 8 characters long.')
      return errors
    }
  },
  methods: {
    onSubmit() {
      const url = ''
      this.$v.$touch()
      this.$axios
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
