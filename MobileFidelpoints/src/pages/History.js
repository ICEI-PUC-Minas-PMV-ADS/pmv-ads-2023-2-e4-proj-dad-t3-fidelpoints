import {React, useState, useEffect} from 'react';
import {View, Text, StyleSheet, FlatList, Alert } from 'react-native';
import { List, Divider, Button } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';
import { useNavigation } from '@react-navigation/core';
import { useLocalSearchParams } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';


let data = [];
let zera =[];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function History() {
    const navigation = useNavigation();
    const {produto, status} = useLocalSearchParams();


    function storeData(data) {
      try {
        const jsonValue = JSON.stringify(data);
        AsyncStorage.setItem('productList', jsonValue);
      //  console.log(jsonValue)
      //  Alert.alert("Test", `Escreveu!`)
      } catch (e) {
        Alert.alert("Test", `Não escreveu!`)
      }
    };
    function getData() {
      try {
        const jsonValue = AsyncStorage.getItem('productList');
        data = JSON.parse(jsonValue);
        let id =Math.floor(Math.random()*100);
        let dataStore = {id: id , produto: produto, status: status };
        data.push(dataStore);
        storeData(data);
      //  console.log(jsonValue);
      //  Alert.alert("TesteRead", "Leu")
      } catch (e) {
        Alert.alert("TesteRead", "Não leu")
      }
    };
  //  const [id, setId] = useState()
    useEffect(()=>{
      
      getData();
      
    //  console.log(data)
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
        keyExtractor={item => item.id}
      />

        <Button 
          icon="cart-outline" 
          style={{width: 20}} 
          buttonColor='#0025bf' 
          mode="contained" 
          onPress={() =>{
            storeData(zera);
            navigation.navigate('Rescue');
            console.log(data);
          }}>
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
