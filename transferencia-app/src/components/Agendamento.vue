<template>
  <div class="form-container">
    <h2>Agendar Transferência</h2>
    <form @submit.prevent="agendarTransferencia">
      <label>
        Conta de Origem:
        <input v-model="transferencia.contaOrigem" type="text" placeholder="Conta Origem" required />
      </label>
      <label>
        Conta de Destino:
        <input v-model="transferencia.contaDestino" type="text" placeholder="Conta Destino" required />
      </label>
      <label>
        Valor:
        <input v-model="transferencia.valor" type="number" placeholder="Valor" required />
      </label>
      <label>
        Data de Transferência:
        <input v-model="transferencia.dataTransferencia" type="date" required />
      </label>
      <button type="submit">Agendar</button>
    </form>
    <p v-if="mensagem" :class="mensagemErro ? 'error' : 'success'">{{ mensagem }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      transferencia: {
        contaOrigem: "",
        contaDestino: "",
        valor: null,
        dataTransferencia: "",
      },
      mensagem: "",
      mensagemErro: false,
    };
  },
  methods: {
    async agendarTransferencia() {
      try {
        const response = await axios.post("http://localhost:8080/transferencias", this.transferencia);
        this.mensagem = response.data;
        this.mensagemErro = false;
        this.resetForm();
      } catch (error) {
        this.mensagem = error.response.data || "Erro ao agendar a transferência.";
        this.mensagemErro = true;
      }
    },
    resetForm() {
      this.transferencia = {
        contaOrigem: "",
        contaDestino: "",
        valor: null,
        dataTransferencia: "",
      };
    },
  },
};
</script>

<style>
.form-container {
  width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}
label {
  display: block;
  margin-bottom: 10px;
}
input {
  width: 100%;
  padding: 8px;
  margin-top: 4px;
  margin-bottom: 15px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
button:hover {
  background-color: #45a049;
}
.success {
  color: green;
}
.error {
  color: red;
}
</style>
