import {cleanup, render, screen, userEvent, waitFor} from '@/shared/util/test-utils';
import {MemoryRouter, Route, Routes} from 'react-router-dom';
import {Mock, afterEach, beforeEach, expect, it, vi} from 'vitest';

import PasswordResetInit from '../PasswordResetInit';

const renderPasswordResetInitPage = () => {
    render(
        <MemoryRouter initialEntries={['/password-reset-init']}>
            <Routes>
                <Route element={<PasswordResetInit />} path="/password-reset-init" />
            </Routes>
        </MemoryRouter>
    );
};

it('should render the password reset init page', () => {
    renderPasswordResetInitPage();

    expect(screen.getByText('Send link to email')).toBeInTheDocument();
});

it('should show error message when email is invalid', async () => {});
