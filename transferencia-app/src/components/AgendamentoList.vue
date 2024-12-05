<template>
  <div class="list-container">
    <h2>Agendamentos</h2>
    <table>
      <thead>
        <tr>
          <th>Conta Origem</th>
          <th>Conta Destino</th>
          <th>Valor</th>
          <th>Taxa</th>
          <th>Data Agendamento</th>
          <th>Data Transferência</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="transferencia in transferencias" :key="transferencia.id">
          <td>{{ transferencia.contaOrigem }}</td>
          <td>{{ transferencia.contaDestino }}</td>
          <td>{{ transferencia.valor }}</td>
          <td>{{ transferencia.taxa }}</td>
          <td>{{ transferencia.dataAgendamento }}</td>
          <td>{{ transferencia.dataTransferencia }}</td>
        </tr>
      </tbody>
    </table>
    <button @click="window.location.reload()">Atualizar agenda</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      transferencias: [],
    };
  },
  async created() {
    try {
      const response = await axios.get("http://localhost:8080/transferencias");
      this.transferencias = response.data;
    } catch (error) {
      console.error("Erro ao buscar transferências:", error);
    }
  },
};
</script>

<style>
.list-container {
  max-width: 800px;
  margin: 50px auto;
  margin-left: 20%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
th {
  background-color: #4caf50;
  color: white;
}
</style>
