<template>
    <div class="edit-chocolate">
      <h1 class="ChocoTitle">Edit Chocolate</h1>
      <form @submit.prevent="submitForm">
        <div class="form-row">
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="chocolate.name" required>
          </div>
          <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" id="price" v-model="chocolate.price" required>
          </div>
        </div>
        <div class="form-row">
          <div class="form-group">
            <label for="type">Type:</label>
            <select id="type" v-model="chocolate.type" required>
              <option value="ORDINARY">Ordinary</option>
              <option value="FOR_COOKING">For Cooking</option>
              <option value="FOR_DRINKING">For Drinking</option>
              <option value="GIFT">Gift</option>
            </select>
          </div>
          <div class="form-group">
            <label for="kind">Kind:</label>
            <select id="kind" v-model="chocolate.kind" required>
              <option value="DARK">Dark</option>
              <option value="MILK">Milk</option>
              <option value="WHITE">White</option>
              <option value="FLAVORED">Flavored</option>
            </select>
          </div>
        </div>
        <div class="form-row">
          <div class="form-group">
            <label for="weight">Weight (grams):</label>
            <input type="number" id="weight" v-model="chocolate.weight" required>
          </div>
          <div class="form-group">
            <label for="image">Image:</label>
            <input type="file" id="image" @change="onFileChange($event)" accept="image/*" required>
          </div>
        </div>
        <div class="form-row">
          <div class="form-group"  height="200px">
            <label for="description">Description:</label>
            <textarea id="description" v-model="chocolate.description" required></textarea>
          </div>
        </div>
        <button type="submit">Edit Chocolate</button>
      </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            chocolate: {
                name: '',
                price: null,
                type: '',
                kind: '',
                weight: null,
                description: '',
                image: null
            }
        };
    },
    methods: {
        submitForm() {
        const chocolateId = this.$route.params.id;
        axios.put(`http://localhost:8080/WebShopAppREST/rest/chocolates/${chocolateId}`, this.chocolate)
            .then(response => {
                console.log('Chocolate updated successfully:', response.data);
                // Opciono: Prikazati poruku korisniku o uspešnom ažuriranju
            })
            .catch(error => {
                console.error('Error updating chocolate:', error);
                // Opciono: Prikazati poruku korisniku o grešci pri ažuriranju
            });
        },
        fetchChocolateDetails() {
            // Dobijanje detalja o čokoladi sa servera
            const chocolateId = this.$route.params.id;
            axios.get(`http://localhost:8080/WebShopAppREST/rest/chocolates/${chocolateId}`)
                .then(response => {
                    // Popunite podatke o čokoladi
                    this.chocolate = response.data;
                })
                .catch(error => {
                    console.error('Error fetching chocolate details:', error);
                });
        }
    },
    mounted() {
        // Pozivamo funkciju za dobijanje detalja o čokoladi kada se komponenta montira
        this.fetchChocolateDetails();
    }
};
</script>

