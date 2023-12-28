<template>
  <div id="app">
    <div class='wrap'>
      <Calendar v-model="selectedDates" :minDate="nextMonthFirstDay" :maxDate="nextMonthLastDay" selectionMode="multiple" inline/>
      <form @submit.prevent="handleSubmit">
        <input v-model="name" placeholder="Name" required />
        <input v-model.number="quota" placeholder="Quota" required />
        <button type="submit">Submit Schedule</button>
      </form>
      <button @click="submitAll">Submit All</button>
      <div class="availabilities">
        <h2>Submitted Schedules</h2>
        <div v-for="(availability, index) in availabilities" :key="index">
          <h3>{{ availability.name }}</h3>
          <p>Quota: {{ availability.quota }}</p>
          <p>Dates: {{ availability.dates.sort((a, b) => a - b).map(date => date.getDate()).join(', ') }}</p>          <button @click="editSchedule(index)">Edit</button>
        </div>
      </div>
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
      nextMonthLastDay: null,
      name: '',
      quota: null,
      availabilities: [],
      editIndex: null
    };
  },
  created() {
    const today = new Date();
    const nextMonth = today.getMonth() + 1;
    this.nextMonthFirstDay = new Date(today.getFullYear(), nextMonth, 1);
    this.nextMonthLastDay = new Date(today.getFullYear(), nextMonth + 1, 0);
  },
  methods: {
    handleSubmit() {
      const availability = {
        name: this.name,
        quota: this.quota,
        dates: this.selectedDates.map(date => new Date(date))
      };
      if (this.editIndex !== null) {
        this.availabilities[this.editIndex] = availability;
        this.editIndex = null;
      } else {
        this.availabilities.push(availability);
      }
      this.resetForm();
    },
    resetForm() {
      this.name = '';
      this.quota = null;
      this.selectedDates = [];
    },
    editSchedule(index) {
      const availability = this.availabilities[index];
      this.name = availability.name;
      this.quota = availability.quota;
      this.selectedDates = availability.dates;
      this.editIndex = index;
    },
    submitAll() {
      console.log(this.availabilities)
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
  background-color: #ff0000;
  color: #000000;
}
</style>