<template>
  <div id="app">
    <div class='wrap'>
      <Calendar v-model="selectedDates" :minDate="nextMonthFirstDay" :maxDate="nextMonthLastDay" selectionMode="multiple" inline/>
      <form @submit.prevent="handleSubmit">
        <input v-model="name" placeholder="Name" required />
        <input v-model.number="quota" placeholder="Quota" required @blur="validateQuota" />
        <button type="submit">Submit Schedule</button>
      </form>
      <button @click="submitAll">Submit All</button>
      <div v-if="responseData">
        <h2>Schedule</h2>
        {{ responseData }}
        <!-- <div id="paypal-button-container"></div> -->
      </div>
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
      editIndex: null,
      availability: null,
      responseData: null,
      nextMonthLastDay: null,
      nextMonthFirstDay: null      
    };
  },
  created() {
    const today = new Date();
    const nextMonth = today.getMonth() + 1;
    this.nextMonthFirstDay = new Date(today.getFullYear(), nextMonth, 1);
    this.nextMonthLastDay = new Date(today.getFullYear(), nextMonth + 1, 0);
  },
  // mounted() {
  //   const script = document.createElement('script');
  //   script.src = 'https://www.paypal.com/sdk/js?client-id=YOUR_CLIENT_ID_HERE&currency=USD';
  //   script.onload = this.setPaypalButton;
  //   document.body.appendChild(script);
  // },
  methods: {
    validateQuota() {
      const nextMonthDays = this.nextMonthLastDay.getDate();
      if (this.quota < 0 || this.quota > nextMonthDays || !Number.isInteger(this.quota)) {
        alert(`Quota must be an integer between 0 and ${nextMonthDays}`);
        this.quota = null;
      }
    },
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
      this.editIndex = index;
      this.name = availability.name;
      this.quota = availability.quota;
      this.selectedDates = availability.dates;
    },
    async submitAll() {
      try {
        // Transform the availabilities' dates to integers
        const transformedAvailabilities = this.availabilities.map(availability => {
          const transformedDates = availability.dates.map(date => {
            const dateObject = new Date(date);
            return dateObject.getDate(); // get the day of the month as an integer
          });
          return { ...availability, dates: transformedDates };
        });

        // Compile availabilities into format that the backend expects
        const data = {
        employeeList: transformedAvailabilities,
        numberOfDays: this.nextMonthLastDay.getDate()
        };

        const response = await axios.post('http://localhost:8080/api/process', data);  //TODO: replace with your API endpoint
        if (response.status === 200) {
          this.responseData = response.data;
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
    },
    // setPaypalButton() {
    //   window.paypal.Buttons({
    //     createOrder: function(data, actions) {
    //       return actions.order.create({
    //         purchase_units: [{
    //           amount: {
    //             value: '10.00' // Set your donation amount here
    //           }
    //         }]
    //       });
    //     },
    //     onApprove: function(data, actions) {
    //       return actions.order.capture().then(function(details) {
    //         alert('Transaction completed by ' + details.payer.name.given_name);
    //       });
    //     }
    //   }).render('#paypal-button-container');
    // }
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