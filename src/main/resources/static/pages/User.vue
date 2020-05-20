<template>
    <div>
        <div class="mb-1">
            <div>
            <p class="title text&#45;&#45;mb-1">
                Welcome, {{usrOb.name}}!
                <v-btn
                        @click="sheet1 = !sheet1"
                >
                    History
                </v-btn>
                <v-bottom-sheet v-model="sheet1">
                    <v-sheet >
                        <v-btn
                                class="mt-6"
                                text
                                color="red"
                                @click="sheet1 = !sheet1"
                        >close</v-btn>
                        <div v-for="cor in productForm">
                            Product: {{cor.productName}} Status: {{cor.description}}
                        </div>
                    </v-sheet>
                </v-bottom-sheet>
            </p>
            </div>
        </div>
        <div v-for="product in products">
            <v-container class="mt-5">
                <v-card
                        class="mx-auto"
                        outlined
                >
                    <v-list three-line>
                        <v-list class="align-self-start">
                            <v-list
                                    class="headline mb-1"
                            >
                                <span class="light-black&#45;&#45;text text&#45;&#45;darken-3">
                                   Seller: {{product.seller.sellerName}} | Product: {{product.productName}}
                                </span>
                            </v-list>
                            <p class="title text&#45;&#45;primary mb-7">Start price: {{product.price}} ₽</p>

                            <p class="subtitle-1 mb-3">
                                Size {{ product.size }}
                            </p>
                            <p>
                                <v-btn text class="light-yellow&#45;&#45;text" @click="show = !show">
                                    Description
                                </v-btn>
                            </p>
                            <v-bottom-sheet v-model="show">
                                <v-sheet>
                                    <v-btn
                                            class="mt-6"
                                            text
                                            color="red"
                                            @click="sheet = !sheet"
                                    >close</v-btn>
                                    <div>
                                        <v-card-text class="subtitle-1">{{product.description}}</v-card-text>
                                    </div>
                                </v-sheet>
                            </v-bottom-sheet>
                        </v-list>
                    </v-list>
                    <v-card-actions>
                        <div class="mt-3">
                            <v-text-field v-model="priceClient">
                                Your Price
                            </v-text-field>
                        </div>
                        <v-btn  color="light-green" dark @click="buy(product)">
                            Send
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-container>
        </div>
    </div>
</template>

<script>
    import product from 'api/product'
    import deal from 'api/deal'
    import message from 'api/message'

    export default {
        name: "User",

        data() {

            return {
                sheet: false,
                sheet1: false,


                usrOb: {
                    clientId:localStorage.getItem("id"),name: localStorage.getItem("name"),
                    phoneNumber: localStorage.getItem("phoneNumber")},
                priceClient:'',
                prod1:{},
                prod2:{description:'', productName:''},
                productForm : [],
                products: [],
                show : false,

            }
        },
        created() {
            product.get().then(result=> {
                result.data.forEach(data => {
                    this.products.push(data)

                })
            })
            deal.get().then(result=> {
                result.data.forEach(data => {
                    if (data.res=="1" && data.fk_id_client==this.usrOb.clientId) {
                        product.getById(data.fk_id_product).then(result2 => {
                            message.getById(data.dealId).then(result3 => {
                                this.prod2 = {productName: result2.data.productName, description: result3.data.text}
                                this.productForm.push(this.prod2)
                            })
                        })
                    } else{
                        if(data.res=="null" && data.fk_id_client==this.usrOb.clientId){
                            product.getById(data.fk_id_product).then(result3 => {
                                this.prod2 = {productName: result3.data.productName, description: "Не просмотрено"}
                                this.productForm.push(this.prod2)
                            })
                        }
                    }

                })
            })
             },
             methods:{
                buy(productss){
                    if (this.priceClient!=''&&this.priceClient.match(/[a-z]/i)==null){
                        deal.add({
                            fk_id_client: productss.fk_seller,
                            fk_id_product: productss.productId,
                            priceClient: this.priceClient,
                            res:'null'})
                        .then(result=>{
                            console.log(result)
                            if (result!=null){
                                alert("Send request")
                                location.reload()
                            }
                        })
                    }
                 }
             }

    }
</script>

<style scoped>

</style>