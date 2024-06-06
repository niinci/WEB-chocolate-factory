<template>
    <title>Chocolate Factories</title>
    <header>
        <div class="menu-icon">&#9776;</div>
        <div class="header-title">Factories</div>
    </header>
    <div class="zaglavlje">
       
        <div class="welcome-text">
            <h1>WELCOME</h1>
            <p>Here you can find the best chocolates available in the region and beyond.</p>
        </div>
    </div>
    <div class="factories">
        <h1>Chocolate factories</h1>
        <div class="factories-section">
            <div class="factory-category">
                <div class="card-container">
                    <a href="#" class="card" v-for="f in factories" :key="f.Id" @click.prevent="goToFactoryInfo(f)">
                        <img :src="getFactoryImagePath(f.logo)" alt="Factory Logo" class="card-image"/>
                        <div class="card-title">{{f.name}}</div>
                        <div class="card-location">Adress:{{f.location.address}}</div>
                        <div class="card-rating">Rating: {{f.rating}}</div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';


const router = useRouter();

const factories = ref([]);

onMounted(() => {
  fetchFactories();
});

function fetchFactories() {
  axios.get('http://localhost:8080/WebShopAppREST/rest/factories/')
    .then(response => {
      factories.value = response.data;
    })
    .catch(error => {
      console.error('Error loading factories:', error);
      alert('Došlo je do greške prilikom učitavanja fabrika.');
    });
}

function goToFactoryInfo(factory){
        this.error = factory.id;
       router.push({path: '/factoryInfo', query: {id:factory.id}});
   }

   const getFactoryImagePath = (imageName) => {
    return new URL(`../assets/FactoryLogos/${imageName}`, import.meta.url);
};
</script>

<style>
html, body {
    margin: 0;
    padding: 0;
    width: 100%;
    height: 100%;
    font-family: Arial, sans-serif;
    position: relative; /* Postavljamo relativan položaj da bismo mogli dodati pseudo-element */
}

body::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url('../assets/pozadina.jpg') no-repeat center center fixed;
    background-size: cover;
    opacity: 0.1; /* Primena opacity samo na pozadinu */
    z-index: -1; /* Postavljanje z-index-a na manje od ostatka sadržaja */
}
header {
    background-color: #cdb517;
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

.welcome-text h1 {
    font-size: 80px; /* Increase font size for welcome heading */
}
.zaglavlje {
    display: flex;
    align-items: top;
    justify-content: space-between;
    background-color: transparent; 
    margin-top: 65%;
    padding-top: 400px;
    width: 109.2%;
    box-sizing: border-box;
}

.welcome-text {
    flex-grow: 2;
    text-align: top;
    font-size: 40px;
    color: white; /* Set text color to gold */
    margin-right: 40px; /* Add right margin for spacing */
    font-family: 'Times New Roman', Times, serif; /* Set font */
    background-color: transparent; /* Remove background color */
}



.factories {
    padding: 20px;
    text-align: center;
}
h1 {
    margin-top: 20px;
    font-size: 36px;
}
.factories-section {
    display:flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 40px; /* Space between the sections */
    margin-top: 20px;
    margin-left: -30px;
}
.factory-category {
    width: 100%;
}
.factory-category h2 {
    margin-bottom: 20px;
    font-size: 24px;
    text-align: left;
}
.card-container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px; /* Add space between cards */
}
.card {
    background-color: #d2b512;
    width: calc(20% - 40px); /* Adjust width based on the desired number of cards per row */
    padding: 20px;
    box-sizing: border-box;
    text-align: center;
    border-radius: 10px;
    text-decoration: none;
    color: white;
    transition: background-color 0.3s ease;
}
.card:hover {
    background-color: #dbde1f;
}
.card-image {
    width: 100%;
    height: 150px; /* Set a fixed height */
    object-fit: cover; /* Ensure the image covers the entire area without stretching */
    border-radius: 50%;
}
.card-title {
    margin-top: 15px;
    font-size: 18px;
    font-weight: bold;
}
.card-location, .card-rating {
    margin-top: 10px;
    font-size: 16px;
}
</style>