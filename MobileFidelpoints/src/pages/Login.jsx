import React, {useState } from "react";
import { View, StyleSheet, Text, Alert } from "react-native";
import { TextInput, Button, Appbar } from "react-native-paper";
import Header from "../components/Header";
import { useNavigation } from "@react-navigation/core";

export default function Login(){
    const data = {
        id: 1,
        user: "edglei",
        password:"12345"
    }
    const [user, setUser] = useState();
    const [pass, setPass] = useState();
    const navigation = useNavigation();
    const testLogin = () => {
        if(user === data.user && pass === data.password ){
            navigation.navigate('Rescue', {userId: data.id, user: data.user })
        }else Alert.alert("Atenção", "Usuário ou senha não confere!")
    }

    return(
        <View style={{flex: 1, backgroundColor:'#FFEBCD'}} >
            <Appbar.Header >
                <Appbar.Content 
                    title='Fidelpoints' 
                    titleStyle={{color:"#fff"}} 
                    style={styles.bar} 
                />
            </Appbar.Header>
            <Text style={{marginBottom: 90, marginHorizontal:20, fontSize: 20, fontWeight:'bold'}}>
                
                Seja bem-vindo ao Fidelpoints seu sistema de fidelidade</Text>
            
            <View style ={styles.container} >
            <Text style ={{marginBottom: 10}}>Digite usuário e senha para logar!</Text>
                <TextInput
                    label="Usuário"
                    backgroundColor= '#fff'
                    onChangeText={text => setUser(text)}
                    left ={<TextInput.Icon icon='account' />}
                />
                <TextInput
                    label="Senha"
                    backgroundColor= '#fff'
                    secureTextEntry
                    onChangeText={text => setPass(text)}
                    left ={<TextInput.Icon icon='key'/>}
                    style={{marginTop: 10}}
                />
                <Button 
                icon="login" 
                style={{width: 130, marginTop: 30, alignSelf:'center'}} 
                buttonColor='#0025bf' 
                mode="contained" 
                onPress={testLogin}>Logar</Button>
            </View>
        </View>
    )
}



const styles = StyleSheet.create({
    container: {
      backgroundColor: '#FFFACD',
      justifyContent: 'center',
      marginHorizontal: 30,
      borderRadius: 10
    },
    bar:{
        backgroundColor:"#0025bf", 
        textDecorationColor:"#fff",
        margin:10,
        borderRadius: 5,
        height: 30,
        alignItems:'center',
        justifyContent: 'center'

    } 

  });