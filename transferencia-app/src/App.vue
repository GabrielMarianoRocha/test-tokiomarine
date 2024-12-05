<template>
  <div id="app">
    <Agendamento />
    <AgendamentoList />
  </div>
</template>

<script>
  import Agendamento from "./components/Agendamento.vue";
  import AgendamentoList from "./components/AgendamentoList.vue";

export default {
  components: {
    Agendamento,
    AgendamentoList,
  },
};
</script>

<style>
  #app {
    font-family: Arial, sans-serif;
    text-align: center;
    color: #333;
    margin: 20px;
  }
</style>