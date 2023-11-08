import React from "react";
import { View, Text, StyleSheet } from "react-native";
import { Button } from "react-native-paper";
import { useNavigation } from "@react-navigation/core";
//import { white } from "react-native-paper/lib/typescript/styles/themes/v2/colors";

export default function Begin(){
    const navigation = useNavigation();

    return(
        <View style={styles.container}>
            <Text style={styles.textTitle}> Seja bem Vindo ao FidelPoints!!</Text>
            <Button 
                icon="shopping-outline" 
                style={{width: 130, marginTop: 30}} 
                buttonColor='#000000' 
                mode="contained" 
                onPress={() => navigation.navigate('Rescue')}>Entrar </Button>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      borderTopEndRadius: 20,
      borderTopStartRadius: 20,
      marginTop: 35,
      backgroundColor: '#0025bf',
      justifyContent: 'center',
      alignItems: 'center'
    },
    textTitle: {
        color: '#fff', 
        fontSize: 20,
        fontWeight: 'bold'
    }
  });