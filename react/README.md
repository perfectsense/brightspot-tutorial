# React / Next.js / GraphQL / Typescript

This is an example React application that leverages Next.js for its server side rendering and dynamic routing to pull content from Brightspot's GraphQL API using the Apollo client, written in Typescript to take advantage of the strict types created by the GraphQL code generator making the interaction with GraphQL as seamless as possible.

## Prerequisites

- Node 16 or later
- Yarn 1.22 or later

## Getting Started

### First time build

```yarn install```

### For local development

```yarn dev```

Server is accessible at [http://localhost:3000](http://localhost:3000)  🎉

## Configure GraphQL

The app needs to know how to access Brightspot's GraphQL endpoint for the purpose of build time code generation as well for runtime query execution. The settings need to be configured in a `.env` file. You can copy the provided `example.env.local` file and add your project specific settings to it. You'll need to configure the GraphQL endpoint URL, and optionally a Client ID and Secret if the endpoint is protected. You can see the endpoint URL as well as furnish API keys from Brightspot by going to `Menu --> Admin --> APIs` in the CMS.

Next, you should add all the GraphQL queries you wish to execute to the `components/graphql` folder each in a `.graphql` file, 1 file per query, e.g.

_ImageById.graphql_

```graphql
query ImageById($id: ID) {
  Image(id: $id) {
    title
  }
}
```

Using Brightspot's GraphQL Explorer found in `Menu --> Developer --> GraphQL Explorer` is a great way to generate the queries.

You can then run the graphql code generator to create the Typescript types representing both your queries and the schema as a whole. Make sure that the Brightspot server is up and running before executing the command!

```yarn codegen```

## Calling the GraphQL API

Upon success of the code generator, you should see several generated files.

The `graphql.schema.json` and `fragmentTypes.json` contain the raw schema metadata used by the Apollo client for things like caching.

The `.graphql.tsx` file contains all the Typescript types representing the Graphql schema's input and output types, as well as React hooks for each of your queries that allow you to execute them and retrieve the result. Ex.

```typescript
 const { data, loading, error } = useImageByIdQuery({
    variables: {
        id: '' // value for 'id'
    },
})
```

There is a final set of generated files that address a slightly more nuanced use case. With the default React hook provided above, depending on the state of the application, the query may run server side OR client side. In the event that it is executed client side, the actual call to the Brightspot GraphQL endpoint will be visible in the user's browser network tab, including any configured API Client ID and Secret. If it's important to you to obfuscate or hide this data, the final set of generated files aim to address that concern. In the `generated` folder there will be another `.ts` file named after your query's `.graphql` file (e.g. `imageById.ts`) and in it is a different React hook that will essentially force the query to be executed server side. It does this by calling an auto-generated Next.js API endpoint seen in the `pages/api` folder with a similar name (e.g. `imageById.js`). This acts as a proxy for the real GraphQL API call. So, when your query is executed client side, you'll actually see a call to `/api/imageById` in the network tab with any associated input params, and then the _actual_ API call will be handled server side by that endpoint thus hiding the details about the real endpoint. Invoking the query in this way uses the exact same syntax as described above, you're merely importing the function from a different file (e.g. `imageById.ts` instead of `graphql.tsx`).
