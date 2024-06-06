<template>
    <div class="add-chocolate">
      <h1 class="ChocoTitle">Add New Chocolate</h1>
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
        <button type="submit">Add Chocolate</button>
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
            // Poziv funkcije za dodavanje čokolade na server
            axios.post('http://localhost:8080/WebShopAppREST/rest/chocolates/', this.chocolate)
                 .then(response => {
                     console.log('Chocolate added successfully:', response.data);
                     // Resetuj polja forme nakon uspešnog dodavanja
                     this.resetForm();
                 })
                 .catch(error => {
                     console.error('Error adding chocolate:', error);
                 });
        },
        resetForm() {
            // Resetuj polja forme na početne vrednosti
            this.chocolate = {
                name: '',
                price: null,
                type: '',
                kind: '',
                weight: null,
                description: '',
                image: null
            };
        },
        onFileChange(event) {
            // Implementacija logike za promenu slike
            // Možete postaviti sliku na svojstvo čokolade
            this.chocolate.image = event.target.files[0];
        }
    }
};
</script>

