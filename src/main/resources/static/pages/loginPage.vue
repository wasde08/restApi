<template>
    <v-card class="ma-auto" width="40%" >
            <v-tabs>
                <v-tab >Sign In</v-tab>
                <v-tab >Sign Up</v-tab>
                <v-tab-item>
                    <form>
                        <v-layout row class="mx-0">
                            <v-text-field
                                    v-model="logUser"
                                    label= "Your login"
                            >
                            </v-text-field>
                        </v-layout>
                        <v-layout row class="mx-0">
                            <v-text-field
                                    v-model="passUser"
                                    type="password"
                                    label="Your password"
                            >
                            </v-text-field>
                        </v-layout>
                        <input type="radio" id="one" value="Продавец" v-model="picked" >
                        <label for="one">Seller</label>
                        <br>
                            <input type="radio" id="two" value="Покупатель" v-model="picked">
                            <label for="two">Client</label>
                        <br>
                        <v-btn v-if="logUser!=''&&passUser!=''&&picked!=''" @click="signin">Sign In</v-btn>
                    </form>
                </v-tab-item>
                <v-tab-item>
                    <form>
                        <v-text-field
                                v-model="logUser"
                                label="Your login"
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="passUser"
                                type="password"
                                label="Choose password"
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="passUser2"
                                type="password"
                                label="Confirm password "
                        >
                        </v-text-field>
                        <v-text-field
                                v-model="phoneNumber"
                                label="Mobile Number"
                        >
                        </v-text-field>
                        <input type="radio" id="one1" value="Продавец" v-model="picked2" >
                        <label for="one">Seller</label>
                        <br>
                        <input type="radio" id="two1" value="Покупатель" v-model="picked2">
                        <label for="two">Client</label>
                        <br>
                        <v-btn v-if ="logUser!=''&&passUser!=''&&phoneNumber!=''&&
                        passUser2!=''&&passUser==passUser2&&picked2!=''" @click="signup"
                        >
                            Sign Up</v-btn>
                    </form>
                </v-tab-item>
            </v-tabs>
    </v-card>
</template>
<script>
    import user from 'api/user.js'
    import seller from 'api/seller.js'

    export default {
        data(){
            return {
                name: "loginPage",
                logUser: '',
                passUser: '',
                picked: '',
                picked2: '',
                phoneNumber:'',
                passUser2:''
            }
        },
        methods: {
            signin() {
                if(this.picked=="Покупатель") {
                    user.add({
                        name: this.logUser,
                        passuser: this.passUser,
                        check : '0'},).then(response => {
                        if (response.data==""){
                            alert("Wrong date!")
                        } else {
                            localStorage.setItem("id",response.data.clientId)
                            localStorage.setItem("name",response.data.name)
                            localStorage.setItem("phoneNumber",response.data.phoneNumber)
                            this.$router.push('/user')
                            location.reload()
                        }
                    })
                }else
                {
                    seller.add({
                        sellerName: this.logUser,
                        passUser: this.passUser,
                        check: '0'
                    },).then(response => {
                        if (response.data == "") {
                            alert("Wrong date!")
                        } else {
                            localStorage.setItem("sellerId",response.data.sellerId)
                            localStorage.setItem("sellerName",response.data.sellerName)
                            this.$router.push('/sellers')
                            location.reload()
                        }
                    })
                }
            },
            signup(){
                if(this.picked=="Покупатель") {
                    if ((this.phoneNumber.match(/[a-z]/i) != null)) {
                        alert('Invalid phone number')
                    } else {
                        user.add({
                            name: this.logUser,
                            passuser: this.passUser,
                            phoneNumber: this.phoneNumber,
                            check: '1'
                        }).then(response => {
                            if (response.data == "") {
                                alert('Login already used')
                            } else {
                                alert('Good job!')
                                window.location.reload()
                            }

                        })
                    }
                }else {
                    seller.add({
                        sellerName: this.logUser,
                        passUser: this.passUser,
                        experience: this.phoneNumber,
                        check: '1'
                    }).then(response => {
                        if (response.data == "") {
                            alert('Login already used')
                        } else {
                            alert('Good job!')
                            window.location.reload()
                        }

                    })
                }
            }
        }

    }
</script>

<style>
</style>