import {React, useState, useEffect} from 'react';
import {View, Text, StyleSheet, FlatList, Alert } from 'react-native';
import { List, Divider, Button } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';
import { useNavigation } from '@react-navigation/core';
import { useLocalSearchParams } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';


let data = [];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function History() {
    const navigation = useNavigation();
    const {produto, status} = useLocalSearchParams();


    async function storeData(data) {
      try {
        const jsonValue = JSON.stringify(data);
        await AsyncStorage.setItem('productList', jsonValue);
        console.log(jsonValue)
        Alert.alert("Test", `Escreveu!`)
      } catch (e) {
        Alert.alert("Test", `Não escreveu!`)
      }
    };
    async function getData() {
      try {
        const jsonValue = await AsyncStorage.getItem('productList');
        data = JSON.parse(jsonValue);
        console.log(jsonValue);
        Alert.alert("TesteRead", "Leu")
      } catch (e) {
        Alert.alert("TesteRead", "Não leu")
      }
    };
    const [id, setId] = useState(1)
    useEffect(()=>{
      
      getData();
      data.ID = id;
      let dataStore = {produto: produto, status: status };
      console.log(dataStore)
      
      data.push(dataStore);
      storeData(data);
      console.log(data)
    //  
      
       },[])

    const renderItem = ({item}) => (
    <>
      <List.Item 
        title={item.produto} 
        right={() => 
        <List.Item 
          title ={item.status}
        />}
     />
     <Divider theme={{ colors: { primary: 'green' } }} 
    />
  </>)
  return (

    <View style={styles.container}>
        <Header title = "Histórico de Resgate"/>
        <List.Item 
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Loja' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Status da troca'
            />
          }
        />
        <Divider style={{height: 3, backgroundColor: "#0025bf"}} />
        <View style={{color: '#000', height:10 }}/>
        <FlatList
        data={data}
        renderItem={renderItem}
        keyExtractor={item => item.ID}
      />

        <Button 
          icon="cart-outline" 
          style={{width: 20}} 
          buttonColor='#0025bf' 
          mode="contained" 
          onPress={() => navigation.navigate('Rescue')}>
        </Button>
        <StatusBar style="auto" />
    </View>
    
    );
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff'
    },
    listFirst: {
        fontWeight: 'bold' ,
        marginTop: 50 }
  });
