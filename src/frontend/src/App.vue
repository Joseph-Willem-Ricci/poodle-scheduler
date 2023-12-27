<template>
  <div id="app">
        <div class='wrap'>
          <Calendar v-model="selectedDates" :minDate="nextMonthFirstDay" :maxDate="nextMonthLastDay" selectionMode="multiple"  @show="resetSelectedDates" />
        </div>
  </div>
</template>

<script>
import Calendar from 'primevue/calendar';

export default {
  components: {
    Calendar
  },
  data() {
    return {
      selectedDates: [],
      nextMonthFirstDay: null,
      nextMonthLastDay: null
    };
  },
  created() {
    const today = new Date();
    const nextMonth = today.getMonth() + 1;
    this.nextMonthFirstDay = new Date(today.getFullYear(), nextMonth, 1);
    this.nextMonthLastDay = new Date(today.getFullYear(), nextMonth + 1, 0);
    this.selectedDates = [this.nextMonthFirstDay];
  },
  methods: {
    getNextMonthFirstDay() {
      const today = new Date();
      const nextMonth = new Date(today.getFullYear(), today.getMonth() + 1, 1);
      return nextMonth;
    },
    resetSelectedDates() {
      this.selectedDates = [];
    }
  }
}
</script>

<style>
.wrap {
    margin: 0px auto;
    max-width: 250px;
}
.p-calendar .p-highlight {
  background-color: #ff0000; /* Change this to your desired color */
  color: #000000;
}
</style>