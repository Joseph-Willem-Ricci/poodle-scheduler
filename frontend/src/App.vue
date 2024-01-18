<template>
  <div id="app">
    <div class='wrap'>
      <div v-if="!monthSelected">
        <h2>Select a month to schedule</h2>
        <Calendar v-model="selectedMonth" view="month" dateFormat="mm/yy" @update:modelValue="handleMonthSelect" />
      </div>
      <div v-else>
        <Calendar v-model="selectedDates" :minDate="firstDayOfMonth" :maxDate="lastDayOfMonth" selectionMode="multiple" inline/>
        <form @submit.prevent="handleSubmit">
          <input v-model="name" placeholder="Name" required />
          <input v-model.number="quota" placeholder="Quota" required @blur="validateQuota" />
          <button type="submit">Submit Availability</button>
        </form>
        <button @click="submitAll">Get Schedule</button>
      </div>
      <div v-if="schedule">
        <h2>Employee Shifts</h2>
        <div v-for="(shifts, employee) in employeeShifts" :key="employee">
          <h3>{{ employee }}</h3>
          <div>Shifts: {{ shifts.join(', ') }}</div>
        </div>
        <p>Donations can be made via PayPal to <a :href="'https://paypal.me/jwricci'" target="_blank">https://paypal.me/jwricci</a></p>
        <h2>Schedule</h2>
        <div v-for="(employee, day) in schedule" :key="day">
          {{ day }}: {{ employee }}
        </div>
      </div>
      <div class="availabilities">
        <h2 v-if="availabilities.length">Employee Availabilities</h2>
        <div v-for="(availability, index) in availabilities" :key="index">
          <h3>{{ availability.name }}</h3>
          <p>Quota: {{ availability.quota }}</p>
          <p>Dates: {{ availability.dates.sort((a, b) => a - b).map(date => date.getDate()).join(', ') }}</p>
          <button @click="editSchedule(index)">Edit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Calendar from 'primevue/calendar';
import axios from 'axios'

export default {
  components: {
    Calendar
  },
  data() {
    return {
      name: '',
      selectedDates: [],
      availabilities: [],
      quota: null,
      schedule: null,
      editIndex: null,
      availability: null,
      selectedMonth: null,
      employeeShifts: null,
      monthSelected: false
    };
  },
  computed: {
    firstDayOfMonth() {
      if (this.selectedMonth) {
        const month = this.selectedMonth.getMonth();
        const year = this.selectedMonth.getFullYear();
        return new Date(year, month, 1);
      }
      return null;
    },
    lastDayOfMonth() {
      if (this.selectedMonth) {
        const month = this.selectedMonth.getMonth();
        const year = this.selectedMonth.getFullYear();
        return new Date(year, month + 1, 0);
      }
      return null;
    }
  },
  methods: {
    handleMonthSelect() {
      this.monthSelected = true;
      this.selectedDates = this.getAllDatesInMonth();
    },
    getAllDatesInMonth() {
      // The calendar begins will all dates in month selected by default as a workaround
      // for how the calendar only opens to the current month when no dates are selected
      // handleSubmit() and editSchedule() convert when reading/writing availabilities
      const dates = [];
      const year = this.selectedMonth.getFullYear();
      const month = this.selectedMonth.getMonth();
      const date = new Date(year, month + 1, 0);
      const numDays = date.getDate();
      for (let i = 1; i <= numDays; i++) {
        dates.push(new Date(year, month, i));
      }
      return dates;
    },
    validateQuota() {
      const numDays = this.lastDayOfMonth.getDate()
      if (this.quota < 0 || this.quota > numDays || !Number.isInteger(this.quota)) {
        alert(`Quota must be an integer between 0 and ${numDays}`);
        this.quota = null;
      }
    },
    handleSubmit() {
      // convert unselected dates to selected dates
      const allDatesInMonth = this.getAllDatesInMonth().map(date => date.getTime());
      const selectedDatesTimestamps = this.selectedDates.map(date => new Date(date).getTime());
      const unselectedDates = allDatesInMonth.filter(date => !selectedDatesTimestamps.includes(date));
      const availability = {
        name: this.name,
        quota: this.quota,
        dates: unselectedDates.map(date => new Date(date))
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
      this.handleMonthSelect();  // resets selected dates to all dates
    },
    editSchedule(index) {
      const availability = this.availabilities[index];
      this.editIndex = index;
      this.name = availability.name;
      this.quota = availability.quota;
      // convert unselected dates to selected dates for display
      const allDatesInMonth = this.getAllDatesInMonth().map(date => date.getTime());
      const availabilityDatesTimestamps = availability.dates.map(date => new Date(date).getTime());
      this.selectedDates = allDatesInMonth.filter(date => !availabilityDatesTimestamps.includes(date)).map(date => new Date(date));
    },
    async submitAll() {
      try {
        // Transform the availabilities' dates to integers
        const transformedAvailabilities = this.availabilities.map(availability => {
          const transformedDates = availability.dates.map(date => {
            const dateObject = new Date(date);
            return dateObject.getDate();
          });
          return { ...availability, availability: transformedDates };
        });

        // Compile availabilities into format that the backend expects
        const data = {
        employeeList: transformedAvailabilities,
        numberOfDays: this.lastDayOfMonth.getDate()
        };

        const response = await axios.post('https://pet-poodle-scheduler.koyeb.app/api/process', data);
        // const response = await axios.post('http://localhost:8080/api/process', data);
        if (response.status === 200) {
          this.schedule = response.data.schedule;
          this.employeeShifts = response.data.employeeShifts;
        }
        else if (response.status === 500) {
          console.error("Error: ", response.data)
          alert('Server error: ' + response.data.message)
        } else {
          alert('Something went wrong');
        }
      } catch (error) {
          console.error("Error: ", error)
          alert(error.response.data);
      }
    }
  }
}
</script>

<style>
.wrap {
    margin: 0px auto;
    max-width: 1000px;
}

.p-calendar td span {
  background-color: #ff0000;
  color: #000000;
}

.p-calendar .p-highlight {
  background-color: #ffffff;
  color: #000000;
}

.p-calendar .p-disabled {
  background-color: #ffffff;
  color: #ffffff;
}
</style>