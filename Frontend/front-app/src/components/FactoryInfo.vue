<template>
    <title>Factory Information</title>
    <header>
        <div class="menu-icon">&#9776;</div>
        <div class="header-title">Factory Information</div>
    </header>
    <div class="content">
        <div class="factory-info">
            <h1>{{ factory.name }}</h1>
            <div class="info-section">
                <p><strong>Working hours:</strong> {{ factory.openTime }} - {{factory.closeTime}} </p>
                <p><strong>Status:</strong> {{ isOpen(factory.isOpen) }}</p>
                <p><strong>Location:</strong> {{ factory.location.address }}</p>
                <div class="logo-container">
                    <img :src="getFactoryImagePath(factory.logo)" alt="Factory Logo" class="factory-logo">
                </div>
                <p>Rating:{{ factory.rating }} </p>
             <!--  <div v-if="comments && comments.length"> --> 
                 <div>
                    <h2>Comments</h2>
                    <ul class="comments-list">
                        <li v-for="(comment) in comments">{{ comment.text }}</li>
                    </ul>
                </div>
            </div>
            <div class="chocolate-list">
                <h2>Chocolates</h2>
                <div class="card-container">
                    <div class="card" v-for="chocolate in factory.chocolates">
                        <img :src="getChocolateImagePath(chocolate.image)" alt="Chocolate Image" class="card-image">
                        <div class="card-title">{{ chocolate.name }}</div>
                        <div class="card-description">{{ chocolate.description }}</div>
                        <div class="card-description">Price:{{ chocolate.price }}$</div>
                        <div class="card-description">Weight:{{ chocolate.weight }}g</div>
                        <div class="card-description">Type:{{ formatType(chocolate.type) }}</div>
                        <div class="card-description">Kind:{{ formatKind(chocolate.kind) }}</div>
                        <div class="card-description">Is avaible:{{ isAvaible(chocolate.inStock) }}</div>
                        <div class="card-description">Quantity:{{ chocolate.quantity }}</div>
                        <div class="card-buttons">
                            <button @click="editChocolate(chocolate.id)" class="edit-button">Update</button>
                            <button @click="deleteChocolate(chocolate.id)" class="delete-button">Delete</button>
                        </div>
                    </div>
                </div>
                <button @click="addChocolate" class="add-button">Add new chocolate</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const route = useRoute();
const router = useRouter();
const comments = ref([]);
const factory = ref({
    id: "",
    name: "",
    openTime: "",
    closeTime: "",
    location: "",
    logo: "",
    isOpen: "",
    rating: 0,
    chocolates: []
})	
	
onMounted(() => {
  if (route.query.id) {
    const factoryId = route.query.id;
    fetchFactoryDetails(factoryId);
    getComments(factoryId);
  }
});


const fetchFactoryDetails = (factoryId) => {
  axios.get(`http://localhost:8080/WebShopAppREST/rest/factories/${factoryId}`)
    .then(response => {
      factory.value = response.data;
    })
    .catch(error => {
      console.error('Error loading factory details:', error);
      alert('Došlo je do greške prilikom učitavanja detalja fabrike.');
    });
};

const getComments = (factoryId) => {
  axios.get(`http://localhost:8080/WebShopAppREST/rest/comments/${factoryId}`)
    .then(response => {
        comments.value = response.data;
    })
    .catch(error => {
      console.error('Error loading comment details:', error);
      alert('Došlo je do greške prilikom učitavanja detalja fabrike.');
    });
};

function addChocolate() {
    // Preusmeri korisnika na formu za dodavanje nove čokolade
    router.push({ name: 'addChoco' });
}

function editChocolate(chocolateId) {
    // Preusmeri korisnika na formu za uređivanje čokolade sa odgovarajućim ID-om
    router.push({ name: 'editChoco', params: { id: chocolateId } });
}

function deleteChocolate(chocolateId){
    console.log('Deleting product with ID:', chocolateId);
    axios.delete(`http://localhost:8080/WebShopAppREST/rest/chocolates/delete/${chocolateId}`)
    .then(response => {
        console.log('Product deleted successfully:', response.data);
        factory.value.chocolates = factory.value.chocolates.filter(chocolate => chocolate.id !== chocolateId);
        console.log('Updated chocolates list:', factory.value.chocolates);
    })
    .catch(error => {
        console.error('Error deleting product:', error);
    });
}



const getFactoryImagePath = (imageName) => {
    return new URL(`../assets/FactoryLogos/${imageName}`, import.meta.url);
};
const getChocolateImagePath = (imageName) => {
    return new URL(`../assets/Chocolates/${imageName}`, import.meta.url);
};

function isOpen(opened) {
    return opened ? 'Opened' : 'Closed';
}

function isAvaible(avaible) {
    return avaible ? 'Yes' : 'No';
}

function formatType(type) {
    switch (type) {
        case 'ORDINARY':
            return 'Oridnary choclate';
        case 'FOR_COOKING':
            return 'Cooking chocolate';
        case 'FOR_DRINKING':
            return 'Drinking chocolate';
        case 'GIFT':
            return 'Gifting chocolate';
        default:
            return '';
    }
}
function formatKind(kind) {
    switch (kind) {
        case 'DARK':
            return 'Dark';
        case 'MILK':
            return 'Milk';
        case 'WHITE':
            return 'White';
        case 'FLAVORED':
            return 'Flavored';
        default:
            return '';
    }
}



</script>

<style>
html, body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    font-family: Arial, sans-serif;
    background: url('../assets/pozadina.jpg') no-repeat center center fixed;
    background-size: cover;
    color: white;
}
header {
    background-color: #8a722b;
    padding: 10px;
    display: flex;
    align-items: center;
    width: 100%;
    box-sizing: border-box;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1000;
}
.menu-icon {
    font-size: 30px;
    margin-right: 10px;
    cursor: pointer;
}
.header-title {
    color: white;
    font-size: 24px;
    margin: 0;
}
.content {
    padding: 20px;
    text-align: center;
    margin-top: 100%; /* Increased margin-top to push content further down */
    width: 800px; /* Max width for content */
    margin-left: auto; /* Center align */
    margin-right: auto; /* Center align */
}
.factory-info {
    background-color: rgba(0, 0, 0, 0.7);
    padding: 20px;
    border-radius: 10px;
    display: inline-block;
    text-align: left;
    width: 100%; /* Make full width */
    box-sizing: border-box;
}
.info-section {
    margin-bottom: 20px;
}
.factory-logo {
    width: 300px;
    height: auto;
    display: block;
    margin: 10px auto;
}
.comments-list {
    list-style-type: none;
    padding: 0;
}
.card-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin-top: 20px;
}
.card {
    background-color: #3e3e3e;
    width: 28%; /* Increased width */
    padding: 20px;
    box-sizing: border-box;
    text-align: center;
    border-radius: 10px;
    text-decoration: none;
    color: white;
    transition: background-color 0.3s ease;
    margin: 10px;
}
.card:hover {
    background-color: #575757;
}
.card-image {
    width: 100%;
    height: auto;
    border-radius: 10px;
}
.card-title {
    margin-top: 15px;
    font-size: 18px;
    font-weight: bold;
}
.card-description {
    margin-top: 10px;
    font-size: 16px;
}
.card-buttons {
    display: flex;
    justify-content: space-around;
    margin-top: 10px;
}
.edit-button, .delete-button {
    background-color: #8a722b;
    color: white;
    padding: 5px 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
.edit-button:hover, .delete-button:hover {
    background-color: #7a6220;
}
.add-button {
    background-color: #8a722b;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
}
.add-button:hover {
    background-color: #7a6220;
}
</style>