function AnimalList() {
    const animals = [
        {
            id: 1,
            name: "dog",
            img: "https://nypost.com/wp-content/uploads/sites/2/2020/07/dog.jpg?quality=80&strip=all"
        },
        {
            id: 2,
            name: "cat",
            img: "https://img.freepik.com/foto-gratis/gato-azul-aislado_104862-214.jpg?size=626&ext=jpg"
        },
        {
            id: 3,
            name: "bird",
            img: "https://media.cnn.com/api/v1/images/stellar/prod/190414090035-01-cassowary.jpg"
        },
    ];

    const HTMLAnimals = animals.map((animal) => {
        return (
            <li key={animal.id}>
                <h3>{animal.name}</h3>
                <img src={animal.img} alt="animal picture" width={200}/>
            </li>
        )
    })


  return (
    <section>
        <h2>Animals:</h2>
        <ul>
            {HTMLAnimals}
        </ul>
    </section>
  )
}

export default AnimalList