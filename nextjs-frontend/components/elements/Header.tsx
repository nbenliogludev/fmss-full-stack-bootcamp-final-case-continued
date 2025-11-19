'use client'

import { useState } from 'react';
import {
  Dialog,
  DialogPanel,
  PopoverGroup,
} from '@headlessui/react';
import {
  Bars3Icon,
  XMarkIcon,
} from '@heroicons/react/24/outline';
import Link from 'next/link';
import { PhoneIcon, PlayCircleIcon } from '@heroicons/react/20/solid';
import { signIn, signOut, useSession } from "next-auth/react";
import CreateAdModal from './CreateAdModal'; // Adjust the import path as necessary

const callsToAction = [
  { name: 'Watch demo', href: '#', icon: PlayCircleIcon },
  { name: 'Contact sales', href: '#', icon: PhoneIcon },
];

export default function Header() {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const { data: session } = useSession();

  const closeModal = () => setIsModalOpen(false);
  const openModal = () => setIsModalOpen(true);

  const handleSignOut = () => {
    signOut({ callbackUrl: '/discover' });
  };

  return (
    <header className="bg-white">
      <nav aria-label="Global" className="mx-auto flex max-w-7xl items-center justify-between p-6 lg:px-8">
        <div className="flex lg:flex-1">
          <a href="/discover" className="-m-1.5 p-1.5">
            <span className="sr-only">Your Company</span>
            <img alt="" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" className="h-8 w-auto" />
          </a>
        </div>
        <div className="flex lg:hidden">
          <button
            type="button"
            onClick={() => setMobileMenuOpen(true)}
            className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700"
          >
            <span className="sr-only">Open main menu</span>
            <Bars3Icon aria-hidden="true" className="h-6 w-6" />
          </button>
        </div>
        <PopoverGroup className="hidden lg:flex lg:gap-x-12">
          {session?.user && (
            <>
              <button
                onClick={openModal}
                className="text-sm font-semibold leading-6 text-gray-900"
              >
                Create Ad
              </button>
              <Link href="/discover" className="text-sm font-semibold leading-6 text-gray-900">
                Discover
              </Link>
              <Link href="/myAds" className="text-sm font-semibold leading-6 text-gray-900">
                My Ads
              </Link>
              <Link href="/adPackages" className="text-sm font-semibold leading-6 text-gray-900">
                Ad Packages
              </Link>
            </>
          )}
        </PopoverGroup>

        <div className="hidden lg:flex lg:flex-1 lg:justify-end">
          {session?.user ? (
            <button className="text-sm font-semibold leading-6 text-gray-900" onClick={handleSignOut}>
              <span>Log out</span>
              <span aria-hidden="true">&rarr;</span>
            </button>
          ) : (
            <button className="text-sm font-semibold leading-6 text-gray-900" onClick={() => signIn()}>
              <Link href="/login">Log in</Link>
              <span aria-hidden="true">&rarr;</span>
            </button>
          )}
        </div>
      </nav>
      <Dialog open={mobileMenuOpen} onClose={setMobileMenuOpen} className="lg:hidden">
        <div className="fixed inset-0 z-10" />
        <DialogPanel className="fixed inset-y-0 right-0 z-10 w-full overflow-y-auto bg-white px-6 py-6 sm:max-w-sm sm:ring-1 sm:ring-gray-900/10">
          <div className="flex items-center justify-between">
            <a href="#" className="-m-1.5 p-1.5">
              <span className="sr-only">Your Company</span>
              <img
                alt=""
                src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
                className="h-8 w-auto"
              />
            </a>
            <button
              type="button"
              onClick={() => setMobileMenuOpen(false)}
              className="-m-2.5 rounded-md p-2.5 text-gray-700"
            >
              <span className="sr-only">Close menu</span>
              <XMarkIcon aria-hidden="true" className="h-6 w-6" />
            </button>
          </div>
          <div className="mt-6 flow-root">
            <div className="-my-6 divide-y divide-gray-500/10">
              <div className="space-y-2 py-6">
                <button
                  onClick={openModal}
                  className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                >
                  Create Ad
                </button>
                <Link
                  href="/discover"
                  className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                >
                  Discover
                </Link>
                <Link
                  href="/myAds"
                  className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                >
                  My Ads
                </Link>
                <Link
                  href="/adPackages"
                  className="-mx-3 block rounded-lg px-3 py-2 text-base font-semibold leading-7 text-gray-900 hover:bg-gray-50"
                >
                  Ad Packages
                </Link>
              </div>
            </div>
          </div>
        </DialogPanel>
      </Dialog>

      <CreateAdModal isOpen={isModalOpen} onClose={closeModal} />
    </header>
  );
}
