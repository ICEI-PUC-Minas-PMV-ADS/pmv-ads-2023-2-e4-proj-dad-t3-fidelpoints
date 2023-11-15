import React from "react";
import { View, Text, StyleSheet, Image } from "react-native";
import { Button } from "react-native-paper";
import { useNavigation } from "@react-navigation/core";
import firstImage from '../image/Banner.jpg';

export default function Begin(){
    const navigation = useNavigation();

    return(
        <View style={styles.container}>
            <Image
                source={firstImage}
                style={{width: 300, height: 300}}/>
            <Button 
                icon="shopping-outline" 
                style={{width: 130, marginTop: 30}} 
                buttonColor='#000000' 
                mode="contained" 
                onPress={() => navigation.navigate('Login')}>Entrar </Button>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
      flex: 1, 
      marginTop: 0,
      backgroundColor: '#004AAD',
      justifyContent: 'center',
      alignItems: 'center'
    },
    textTitle: {
        color: '#fff', 
        fontSize: 20,
        fontWeight: 'bold'
    }
  });