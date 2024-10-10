import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createMemoryRouter, redirect, RouterProvider } from 'react-router-dom';
import './index.css';
import NotFoundPage from './NotFoundPage.tsx';
import SetUser from './SetUser.tsx';
import Games from './Games.tsx';

function onSessionStart(msg: any) {
  console.log(msg);
  redirect('/games');
}

const router = createMemoryRouter([
  { path: '/', element: <SetUser onSessionStart={onSessionStart} />, errorElement: <NotFoundPage /> },
  { path: '/games', element: <Games />, errorElement: <NotFoundPage /> }
]);
createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
